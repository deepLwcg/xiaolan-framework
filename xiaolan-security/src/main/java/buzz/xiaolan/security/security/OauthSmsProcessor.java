package buzz.xiaolan.security.security;

import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/29
 * @Description OauthEmailProcessor
 */
@Component
public class OauthSmsProcessor extends AbstractOauthProcessor {
    protected OauthSmsProcessor() {
        super(new AntPathRequestMatcher("/oauth/phone", "POST"));
    }

    @Override
    protected Authentication authentication(@NotNull HttpServletRequest request) {
        PhoneInfo phoneInfo = getBodyJson(request, PhoneInfo.class);
        OauthSmsAuthentication authentication = OauthSmsAuthentication.unauthenticated(phoneInfo.getPhone(), phoneInfo.getCode());
        authentication.setDetails(this.authenticationDetailsSource.buildDetails(request));
        return authentication;
    }

    @Data
    public static class PhoneInfo{
        private String phone;
        private String code;
    }
}
