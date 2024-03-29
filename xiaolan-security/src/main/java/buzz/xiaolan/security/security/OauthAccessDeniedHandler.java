package buzz.xiaolan.security.security;

import buzz.xiaolan.security.dto.ApiResponse;
import buzz.xiaolan.security.exception.StatusCode;
import buzz.xiaolan.security.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/26
 * @Description OauthAccessDeniedHandler
 */
@Slf4j
@Component
public class OauthAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error("OauthAccessDeniedHandler:{}", accessDeniedException.getMessage());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        String json = JsonUtils.toJson(ApiResponse.fail(StatusCode.ACCESS_DENIED));
        response.setStatus(StatusCode.ACCESS_DENIED.getStatus().value());
        response.getWriter().write(json);
    }
}
