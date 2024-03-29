package buzz.xiaolan.security.security.processor;

import lombok.NonNull;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/30
 * @Description OauthEmailProcessor
 */
@Component
public class OauthEmailProcessor extends AbstractOauthProcessor {
    protected OauthEmailProcessor() {
        super(new AntPathRequestMatcher("/oauth/email","POST"));
    }

    @Override
    public Authentication authentication(@NonNull HttpServletRequest request) {
        EmailInfo emailInfo = this.getBodyJson(request, EmailInfo.class);
        UsernamePasswordAuthenticationToken unauthenticated = UsernamePasswordAuthenticationToken.unauthenticated(emailInfo.getEmail(), emailInfo.getPassword());
        unauthenticated.setDetails(this.authenticationDetailsSource.buildDetails(request));
        return unauthenticated;
    }

    @Setter
    public static class EmailInfo {
        String email;
        String password;

        public String getEmail() {
            return email == null ? "" : email;
        }

        public String getPassword() {
            return password == null ? "" : password;
        }
    }
}
