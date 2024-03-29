package buzz.xiaolan.security.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.security.web.context.SecurityContextRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/30
 * @Description OauthSecurityContextHolderFilter
 */
public class OauthSecurityContextHolderFilter extends SecurityContextHolderFilter {
    private final SecurityContextRepository securityContextRepository;
    private final AuthenticationFailureHandler failureHandler;


    public OauthSecurityContextHolderFilter(SecurityContextRepository securityContextRepository, AuthenticationFailureHandler failureHandler) {
        super(securityContextRepository);
        this.failureHandler = failureHandler;
        this.securityContextRepository = securityContextRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            SecurityContext securityContext = this.securityContextRepository.loadContext(request).get();
            SecurityContextHolder.setContext(securityContext);
            filterChain.doFilter(request, response);
        } catch (AuthenticationException authenticationException) {
            this.failureHandler.onAuthenticationFailure(request, response, authenticationException);
        } finally {
            SecurityContextHolder.clearContext();
        }
    }
}
