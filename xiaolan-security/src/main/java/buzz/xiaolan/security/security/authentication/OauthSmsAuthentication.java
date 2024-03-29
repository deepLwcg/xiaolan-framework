package buzz.xiaolan.security.security.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collections;
import java.util.Optional;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/30
 * @Description OauthSmsAuthentication
 */
public class OauthSmsAuthentication extends AbstractAuthenticationToken {

    private final Object principal;

    private Object credentials;

    public OauthSmsAuthentication(String phone, String code) {
        super(Collections.emptyList());
        this.principal = phone;
        this.credentials = code;
        setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.credentials = null;
    }

    public static OauthSmsAuthentication unauthenticated(String phone, String code) {
        return new OauthSmsAuthentication(Optional.ofNullable(phone).orElse(""), Optional.ofNullable(code).orElse(""));
    }
}
