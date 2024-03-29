package buzz.xiaolan.security.exception;

import lombok.Getter;
import lombok.ToString;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/28
 * @Description BizException
 */
@Getter
@ToString
public class BizException extends RuntimeException  {

    private final StatusCode statusCode;

    public BizException(StatusCode statusCode) {
        super(statusCode.getMessage());
        this.statusCode = statusCode;
    }


}
