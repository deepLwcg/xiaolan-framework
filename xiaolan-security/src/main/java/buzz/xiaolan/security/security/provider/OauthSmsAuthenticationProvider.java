package buzz.xiaolan.security.security.provider;

import buzz.xiaolan.security.entity.User;
import buzz.xiaolan.security.exception.StatusCode;
import buzz.xiaolan.security.security.authentication.OauthSmsAuthentication;
import buzz.xiaolan.security.security.OauthUserDetailsService;
import buzz.xiaolan.security.security.UserInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/30
 * @Description OauthAuthenticationProvider
 */
public class OauthSmsAuthenticationProvider implements AuthenticationProvider {

    private final OauthUserDetailsService oauthUserDetailsService;

    public OauthSmsAuthenticationProvider(OauthUserDetailsService oauthUserDetailsService) {
        this.oauthUserDetailsService = oauthUserDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        OauthSmsAuthentication oauthSmsAuthentication = (OauthSmsAuthentication) authentication;
        String phone = (String) oauthSmsAuthentication.getPrincipal();
        String code = (String) oauthSmsAuthentication.getCredentials();
        if (!verifyCode(phone, code)) {
            throw new BadCredentialsException(StatusCode.AUTHENTICATION_CAPTCHA_ERROR.name());
        }
        User user = oauthUserDetailsService.getOne(new QueryWrapper<>(User.builder()
                .phone(phone)
                .build()));
        if (user == null) {
            // 创建默认用户
            user = User.builder()
                    .nickname("普通用户")
                    .phone(phone)
                    .isEnabled(true)
                    .build();
            oauthUserDetailsService.save(user);
        }
        UserInfo userInfo = new UserInfo(user);
        UsernamePasswordAuthenticationToken result = UsernamePasswordAuthenticationToken.authenticated(userInfo,
                userInfo.getPassword(), authentication.getAuthorities());
        result.setDetails(authentication.getDetails());
        return result;
    }

    // 验证手机号的验证码
    private boolean verifyCode(String phone, String code) {
        return true;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OauthSmsAuthentication.class.isAssignableFrom(authentication);
    }
}
