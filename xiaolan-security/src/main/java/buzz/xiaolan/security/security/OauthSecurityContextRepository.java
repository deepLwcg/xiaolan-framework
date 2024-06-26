package buzz.xiaolan.security.security;

import buzz.xiaolan.security.exception.StatusCode;
import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/27
 * @Description OauthSecurityContextRepository
 */
@Slf4j
public class OauthSecurityContextRepository implements SecurityContextRepository {

    private final TimedCache<String, Authentication> authenticationsCache = CacheUtil.newTimedCache(JwtManager.EXPIRES * 60 * 1000);

    public final OauthUserDetailsService oauthUserDetailsService;
    private final AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource;
    private final GrantedAuthoritiesMapper authoritiesMapper;

    public OauthSecurityContextRepository(OauthUserDetailsService oauthUserDetailsService, AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource, GrantedAuthoritiesMapper authoritiesMapper) {
        this.oauthUserDetailsService = oauthUserDetailsService;
        this.authenticationDetailsSource = authenticationDetailsSource;
        this.authoritiesMapper = authoritiesMapper;
    }

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        String token = this.getAuthorizationToken(requestResponseHolder.getRequest());
        if (StringUtils.isBlank(token)) {
            return securityContext;
        }
        if (!JwtManager.verify(token)) {
            throw new CredentialsExpiredException(StatusCode.AUTHENTICATION_TOKEN_INVALID.name());
        }
        if (!JwtManager.validateDate(token)) {
            throw new CredentialsExpiredException(StatusCode.AUTHENTICATION_TOKEN_EXPIRED.name());
        }
        JSONObject jsonObject = JwtManager.parseToken(token);
        String id = jsonObject.getStr("id");
        if (StringUtils.isNotBlank(id)) {
            UserDetails userDetails = null;
            if (authenticationsCache.containsKey(id)) {
                Authentication authentication = authenticationsCache.get(id);
                if (authentication != null && authentication.isAuthenticated()) {
                    Object principal = authentication.getPrincipal();
                    if (principal instanceof UserInfo userInfo) {
                        userDetails = userInfo;
                    }
                }
            }
            boolean addCache = false;
            if (userDetails == null){
                userDetails = oauthUserDetailsService.getUserDetailsById(id);
                addCache = true;
            }
            if (userDetails != null) {
                UsernamePasswordAuthenticationToken result = UsernamePasswordAuthenticationToken.authenticated(userDetails,
                        userDetails.getPassword(), this.authoritiesMapper.mapAuthorities(userDetails.getAuthorities()));
                result.setDetails(this.authenticationDetailsSource.buildDetails(requestResponseHolder.getRequest()));
                securityContext.setAuthentication(result);
                if (addCache){
                    authenticationsCache.put(id, result);
                }
            }
        }
        return securityContext;
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = context.getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserInfo userInfo) {
                if (userInfo.getId() != null) {
                    authenticationsCache.put(userInfo.getId(), authentication);
                }
            }
        }
    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        return false;
    }

    private String getAuthorizationToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        // Bearer
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            if (log.isDebugEnabled()) {
                log.debug("Authorization header is not present or is not a Bearer token");
            }
            return null;
        }
        String token = authorization.substring(7);
        if (StringUtils.isBlank(token)) {
            if (log.isDebugEnabled()) {
                log.debug("Authorization Header Bearer is blank");
            }
            return null;
        }
        if (log.isDebugEnabled()) {
            log.debug("Authorization header found for token {}", token);
        }
        return token;
    }
}
