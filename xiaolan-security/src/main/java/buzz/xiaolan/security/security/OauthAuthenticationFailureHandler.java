package buzz.xiaolan.security.security;

import buzz.xiaolan.security.dto.ApiResponse;
import buzz.xiaolan.security.exception.StatusCode;
import buzz.xiaolan.security.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
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
        log.error("OauthAuthenticationFailureHandler:{} {}", exception.getMessage(), exception.getClass());
        response.setContentType("application/json;charset=utf-8");
        String json = JsonUtils.toJson(ApiResponse.fail(StatusCode.AUTHENTICATION_USERNAME_PASSWORD_ERROR));
        response.getWriter().write(json);
    }
}
