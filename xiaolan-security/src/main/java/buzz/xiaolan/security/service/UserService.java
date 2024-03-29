package buzz.xiaolan.security.service;

import buzz.xiaolan.security.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/30
 * @Description UserService
 */
public interface UserService extends IService<User> {

    /**
     * registeredUser
     *
     * @param user  {@link User}
     * @date 2024/3/30 01:03
     */
    void registeredUser(User user);
}
