package buzz.xiaolan.security.controller;

import buzz.xiaolan.security.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/28
 * @Description HomeController
 */
@Slf4j
@RestController
public class HomeController {

    @GetMapping("home")
    public ApiResponse<?> home() {
        return ApiResponse.success();
    }
}
