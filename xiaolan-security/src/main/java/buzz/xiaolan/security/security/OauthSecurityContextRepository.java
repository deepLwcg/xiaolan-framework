package buzz.xiaolan.security.security;

import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
        log.info("loadContext");
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        String token = this.getAuthorizationToken(requestResponseHolder.getRequest());
        try {
            if (JwtManager.verify(token) && JwtManager.validateDate(token)) {
                JSONObject jsonObject = JwtManager.parseToken(token);
                String id = jsonObject.getStr("id");
                if (StringUtils.isNotBlank(id)) {
                    UserDetails userDetails = oauthUserDetailsService.getUserDetails(id);
                    if (userDetails != null) {
                        UsernamePasswordAuthenticationToken result = UsernamePasswordAuthenticationToken.authenticated(userDetails,
                                userDetails.getPassword(), this.authoritiesMapper.mapAuthorities(userDetails.getAuthorities()));
                        result.setDetails(this.authenticationDetailsSource.buildDetails(requestResponseHolder.getRequest()));
                        securityContext.setAuthentication(result);
                    }
                }
            }
        } catch (Exception ex) {
            if (log.isDebugEnabled()) {
                log.debug("JwtManager verify error", ex);
            }
        }
        return securityContext;
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {

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
