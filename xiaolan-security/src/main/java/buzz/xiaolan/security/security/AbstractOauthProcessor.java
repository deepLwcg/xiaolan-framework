package buzz.xiaolan.security.security;

import buzz.xiaolan.security.exception.StatusCode;
import buzz.xiaolan.security.utils.JsonUtils;
import cn.hutool.core.io.IoUtil;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/29
 * @Description AbstractOauthProcessor
 */
@Slf4j
public abstract class AbstractOauthProcessor {

    protected List<MediaType> mediaTypes = MediaType.parseMediaTypes("application/json;charset=UTF-8,application/json");

    @Setter
    protected AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new WebAuthenticationDetailsSource();

    protected final RequestMatcher requestMatcher;


    protected AbstractOauthProcessor(RequestMatcher requestMatcher) {
        this.requestMatcher = requestMatcher;
    }


    protected abstract Authentication authentication(@NonNull HttpServletRequest request);


    protected boolean match(HttpServletRequest request) {
        return requestMatcher.matches(request);
    }

    /**
     * 从request的流中获取json
     *
     * @param request {@link HttpServletRequest}
     * @return com.alibaba.fastjson2.JSONObject
     * @date 2024/3/29 23:03
     */
    protected <T> T getBodyJson(@NonNull HttpServletRequest request, Class<T> clazz) {
        try {
            MediaType mediaType = MediaType.parseMediaType(request.getContentType());
            if (!mediaTypes.contains(mediaType)) {
                throw new BadCredentialsException(StatusCode.USER_REQUEST_PARAM_ERROR.name());
            }
            ServletInputStream inputStream = request.getInputStream();
            String json = IoUtil.readUtf8(inputStream);
            return JsonUtils.toObject(json, clazz);
        } catch (IOException e) {
            log.error("failed to obtain stream for request", e);
            throw new BadCredentialsException(StatusCode.USER_REQUEST_PARAM_ERROR.name());
        }
    }

}
