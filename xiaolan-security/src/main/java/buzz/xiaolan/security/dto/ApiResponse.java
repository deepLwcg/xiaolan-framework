package buzz.xiaolan.security.dto;

import buzz.xiaolan.security.exception.BizException;
import buzz.xiaolan.security.exception.StatusCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/28
 * @Description ApiResponse
 */
@Data
@Builder
public class ApiResponse<T> {

    protected String code;

    protected String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected T data;
    public static <T> ApiResponse<T> success() {
        return success(null);
    }

    public static <T> ApiResponse<T> success(T data) {
        StatusCode statusCode = StatusCode.SUCCESS;
        return ApiResponse.<T>builder()
                .code(statusCode.getCode())
                .message(statusCode.getMessage())
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> fail(StatusCode statusCode) {
        return ApiResponse.<T>builder()
                .code(statusCode.getCode())
                .message(statusCode.getMessage())
                .build();
    }

    public static <T> ApiResponse<T> fail(StatusCode statusCode,String message) {
        return ApiResponse.<T>builder()
                .code(statusCode.getCode())
                .message(message)
                .build();
    }


    public static <T> ApiResponse<T> fail(BizException bizException) {
        return fail(bizException, null);
    }

    public static <T> ApiResponse<T> fail(BizException bizException, T data) {
        StatusCode statusCode = bizException.getStatusCode();
        return ApiResponse.<T>builder()
                .code(statusCode.getCode())
                .message(statusCode.getMessage())
                .data(data)
                .build();
    }


}
