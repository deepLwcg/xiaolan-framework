package buzz.xiaolan.security.utils;

import buzz.xiaolan.security.exception.BizException;
import buzz.xiaolan.security.exception.StatusCode;
import buzz.xiaolan.security.security.UserInfo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/31
 * @Description AuthenticationUtils
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthenticationUtils {


    public static UserInfo getUserInfo() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserInfo) {
                return (UserInfo) principal;
            }
        }
        throw new BizException(StatusCode.AUTHENTICATION_USER_NOT_FOUND);
    }

}
