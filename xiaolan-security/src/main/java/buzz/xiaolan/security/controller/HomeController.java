package buzz.xiaolan.security.controller;

import buzz.xiaolan.security.dto.ApiResponse;
import buzz.xiaolan.security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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


    private UserService userService;

    @GetMapping("home")
    public ApiResponse<?> home() {
        return ApiResponse.success(userService.list());
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
