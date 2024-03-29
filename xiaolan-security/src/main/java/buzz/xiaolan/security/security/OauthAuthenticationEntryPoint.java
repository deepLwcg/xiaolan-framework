package buzz.xiaolan.security.security;

import buzz.xiaolan.security.dto.ApiResponse;
import buzz.xiaolan.security.exception.StatusCode;
import buzz.xiaolan.security.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/26
 * @Description OauthAuthenticationEntryPoint
 */
@Slf4j
@Component
public class OauthAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error("OauthAuthenticationEntryPoint commence: {}", authException.getMessage());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        String json = JsonUtils.toJson(ApiResponse.fail(StatusCode.AUTHENTICATION_ENTRY_POINT));
        response.getWriter().write(json);
    }
}
