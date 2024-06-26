package buzz.xiaolan.security.security.handler;

import buzz.xiaolan.security.dto.ApiResponse;
import buzz.xiaolan.security.exception.StatusCode;
import buzz.xiaolan.security.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/26
 * @Description OauthAuthenticationFailureHandler
 */
@Slf4j
public class OauthAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.error("OauthAuthenticationFailureHandler: {} {}", exception.getMessage(), exception.getClass());
        response.setContentType("application/json;charset=utf-8");
        StatusCode statusCode = StatusCode.AUTHENTICATION_USERNAME_PASSWORD_ERROR;
        if (exception instanceof CredentialsExpiredException credentialsExpiredException) {
            String message = credentialsExpiredException.getMessage();
            statusCode = StatusCode.NameOf(message, StatusCode.AUTHENTICATION_USERNAME_PASSWORD_ERROR);
        }
        String json = JsonUtils.toJson(ApiResponse.fail(statusCode));
        response.setStatus(statusCode.getStatus().value());
        response.getWriter().write(json);
    }
}
