package buzz.xiaolan.security.security.handler;

import buzz.xiaolan.security.dto.AccessToken;
import buzz.xiaolan.security.dto.ApiResponse;
import buzz.xiaolan.security.exception.StatusCode;
import buzz.xiaolan.security.security.JwtManager;
import buzz.xiaolan.security.security.UserInfo;
import buzz.xiaolan.security.utils.JsonUtils;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/26
 * @Description OauthAuthenticationSuccessHandler
 */
public class OauthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserInfo userInfo) {
            DateTime dateTime = DateUtil.date();
            DateTime expiresAt = DateUtil.offsetHour(dateTime, 6);
            String accessToken = JwtManager.generatedToken(Map.<String, String>of("id", userInfo.getId()), expiresAt);
            String refreshToken = JwtManager.generatedRefreshToken(Map.<String, String>of("id", userInfo.getId()));
            String json = JsonUtils.toJson(ApiResponse.success(AccessToken.of(accessToken, refreshToken, expiresAt.getTime())));
            response.getWriter().write(json);
            return;
        }
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(JsonUtils.toJson(ApiResponse.fail(StatusCode.AUTHENTICATION_FAILURE)));
    }
}
