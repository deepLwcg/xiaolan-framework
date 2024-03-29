package buzz.xiaolan.security.security;

import buzz.xiaolan.security.entity.User;
import buzz.xiaolan.security.mapper.UserMapper;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/26
 * @Description OauthUserDetailsService
 */
@Component
public class OauthUserDetailsService extends ServiceImpl<UserMapper, User> implements UserDetailsService, IService<User> {

    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = User.builder()
                .id(IdUtil.simpleUUID())
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .isEnabled(true)
                .build();
        return new UserInfo(user);
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserDetails getUserDetails(String id) {
        User user = User.builder()
                .id(IdUtil.simpleUUID())
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .isEnabled(true)
                .build();
        return new UserInfo(user);
    }

}
