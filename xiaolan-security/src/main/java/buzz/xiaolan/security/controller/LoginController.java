package buzz.xiaolan.security.controller;

import buzz.xiaolan.security.dto.ApiResponse;
import buzz.xiaolan.security.dto.RegisterUserVo;
import buzz.xiaolan.security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/30
 * @Description LoginController
 */
@Slf4j
@RestController
@RequestMapping("api")
public class LoginController {

    private UserService userService;

    @PostMapping("register")
    public ApiResponse<?> register(@RequestBody RegisterUserVo userVo) {
        userService.registeredUser(userVo.toUser());
        return ApiResponse.success();
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
