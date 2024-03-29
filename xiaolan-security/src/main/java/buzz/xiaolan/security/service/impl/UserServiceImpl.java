package buzz.xiaolan.security.service.impl;

import buzz.xiaolan.security.entity.User;
import buzz.xiaolan.security.exception.BizException;
import buzz.xiaolan.security.exception.StatusCode;
import buzz.xiaolan.security.mapper.UserMapper;
import buzz.xiaolan.security.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/30
 * @Description UserServiceImpl
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private PasswordEncoder passwordEncoder;

    @Override
    public synchronized void registeredUser(User user) {
        String email = user.getEmail();
        if (StringUtils.isNotBlank(email)) {
            if (count(new LambdaQueryWrapper<User>().eq(User::getEmail, email)) > 0) {
               throw new BizException(StatusCode.AUTHENTICATION_EMAIL_EXIST);
            }
        }
        String phone = user.getPhone();
        if (StringUtils.isNotBlank(phone)) {
            if (count(new LambdaQueryWrapper<User>().eq(User::getPhone, phone)) > 0) {
                throw new BizException(StatusCode.AUTHENTICATION_PHONE_EXIST);
            }
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        save(user);
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
