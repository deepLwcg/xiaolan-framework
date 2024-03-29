package buzz.xiaolan.security.exception;

import buzz.xiaolan.security.contexts.AppContext;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;

import java.util.Locale;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/28
 * @Description StatusCode
 */
@AllArgsConstructor
@Getter
@ToString
public enum StatusCode {

    SUCCESS(HttpStatus.OK, "00000", "success"),

    BAD_REQUEST(HttpStatus.BAD_REQUEST, "A0002", "bad.request"),

    NO_HANDLER_FOUND(HttpStatus.NOT_FOUND, "A0003", "no.handler.found"),

    USER_REQUEST_PARAM_ERROR(HttpStatus.BAD_REQUEST, "A0444", "request.parameter.error"),

    HTTP_REQUEST_METHOD_NOT_SUPPORTED(HttpStatus.METHOD_NOT_ALLOWED, "A0001", "http.request.method.not.supported"),

    /**
     * 认证失败
     */
    AUTHENTICATION_FAILURE(HttpStatus.UNAUTHORIZED, "A0004", "authentication.failure"),

    /**
     * 验证码错误
     */
    AUTHENTICATION_CAPTCHA_ERROR(HttpStatus.UNAUTHORIZED, "A0009", "authentication.captcha.error"),
    /**
     * 不存在
     */
    AUTHENTICATION_USER_NOT_FOUND(HttpStatus.UNAUTHORIZED, "A0006", "authentication.user.not.found"),

    /**
     * 用户名或密码错误
     */
    AUTHENTICATION_USERNAME_PASSWORD_ERROR(HttpStatus.UNAUTHORIZED, "A0005", "authentication.username.password.error"),

    PROVIDER_NOT_FOUND_EXCEPTION(HttpStatus.UNAUTHORIZED, "A0008", "provider.not.found.exception"),

    /**
     * 访问被拒绝
     */
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "A0006" , "access.denied" ),

    AUTHENTICATION_ENTRY_POINT(HttpStatus.UNAUTHORIZED, "A0007", "authentication.entry.point"),

    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "A0000", "internal.server.error"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

    public String getMessage() {
        return LazyHolder.getMessage(message, message,  LocaleContextHolder.getLocale());
    }


    private static class LazyHolder {
        private static final MessageSource MESSAGE_SOURCE = AppContext.getBean(MessageSource.class);

        private static String getMessage(String code, String defaultMessage, Locale locale) {
            return MESSAGE_SOURCE.getMessage(code, null, defaultMessage, locale);
        }
    }
}
