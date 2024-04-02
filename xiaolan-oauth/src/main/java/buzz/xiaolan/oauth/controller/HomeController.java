package buzz.xiaolan.oauth.controller;

import buzz.xiaolan.oauth.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/4/1
 * @Description HomeController
 */
@Slf4j
@RestController
public class HomeController {

    @GetMapping
    public ApiResponse<?> home() {
        return ApiResponse.success("Hello, World!");
    }

}
