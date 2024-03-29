package buzz.xiaolan.security.exception;

import buzz.xiaolan.security.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/28
 * @Description GlobalExceptionHandler
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    @ExceptionHandler(BizException.class)
    public ResponseEntity<ApiResponse<?>> handleBizException(BizException ex) {
        log.error("BizException: {}", ex.getMessage(), ex);
        StatusCode statusCode = ex.getStatusCode();
        return ResponseEntity.status(statusCode.getStatus())
                .body(ApiResponse.fail(ex));
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NotNull MethodArgumentNotValidException ex,
                                                                  @NotNull HttpHeaders headers,
                                                                  @NotNull HttpStatus status,
                                                                  @NotNull WebRequest request) {
        log.error("MethodArgumentNotValidException: {}", ex.getMessage(), ex);
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        String message = fieldErrors.stream()
                .filter(Objects::nonNull)
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .filter(StringUtils::isNotBlank)
                .map(x -> messageSource.getMessage(x, null, x, LocaleContextHolder.getLocale()))
                .collect(Collectors.joining("&"));
        return ResponseEntity.status(status)
                .body(ApiResponse.fail(StatusCode.USER_REQUEST_PARAM_ERROR, message));
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(@NotNull NoHandlerFoundException ex,
                                                                   @NotNull HttpHeaders headers,
                                                                   @NotNull HttpStatus status,
                                                                   @NotNull WebRequest request) {
        log.error("NoHandlerFoundException: {}", ex.getMessage());
        return ResponseEntity.status(status)
                .body(ApiResponse.fail(StatusCode.NO_HANDLER_FOUND));
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(@NotNull HttpRequestMethodNotSupportedException ex,
                                                                         @NotNull HttpHeaders headers,
                                                                         @NotNull HttpStatus status,
                                                                         @NotNull WebRequest request) {
        log.error("HttpRequestMethodNotSupported: {}", ex.getMessage(), ex);
        return ResponseEntity.status(status)
                .body(ApiResponse.fail(StatusCode.HTTP_REQUEST_METHOD_NOT_SUPPORTED));
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(@NotNull Exception ex,
                                                             Object body,
                                                             @NotNull HttpHeaders headers,
                                                             @NotNull HttpStatus status,
                                                             @NotNull WebRequest request) {
        log.error("ExceptionInternal: {}", ex.getMessage(), ex);
        return ResponseEntity.status(status)
                .body(ApiResponse.fail(StatusCode.BAD_REQUEST));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleOtherException(Exception ex) {
        log.error("Exception: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.fail(StatusCode.SERVER_ERROR));
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

}
