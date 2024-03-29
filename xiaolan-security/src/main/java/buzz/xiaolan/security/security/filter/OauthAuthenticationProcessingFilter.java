package buzz.xiaolan.security.security.filter;

import buzz.xiaolan.security.exception.StatusCode;
import buzz.xiaolan.security.security.processor.AbstractOauthProcessor;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/26
 * @Description OauthAuthenticationProcessingFilter
 */
public class OauthAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private final List<? extends AbstractOauthProcessor> processors;

    public OauthAuthenticationProcessingFilter(RequestMatcher requiresAuthenticationRequestMatcher, AbstractOauthProcessor ... processors) {
        super(requiresAuthenticationRequestMatcher);
        this.processors = List.of(processors);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        for (AbstractOauthProcessor processor : processors) {
            if (processor.match(request)) {
                Authentication authentication = processor.authentication(request);
                return getAuthenticationManager().authenticate(authentication);
            }
        }
        throw new BadCredentialsException(StatusCode.USER_REQUEST_PARAM_ERROR.name());

    }

    @Override
    public void setAuthenticationDetailsSource(AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource) {
        super.setAuthenticationDetailsSource(authenticationDetailsSource);
        for (AbstractOauthProcessor processor : processors) {
            processor.setAuthenticationDetailsSource(authenticationDetailsSource);
        }
    }
}
