package buzz.xiaolan.security.security;

import buzz.xiaolan.security.entity.User;
import buzz.xiaolan.security.exception.StatusCode;
import buzz.xiaolan.security.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/26
 * @Description OauthUserDetailsService
 */
@Slf4j
@Component
public class OauthUserDetailsService extends ServiceImpl<UserMapper, User> implements UserDetailsService, IService<User> {

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = getOne(new QueryWrapper<>(User.builder()
                .isEnabled(true)
                .email(email)
                .build()));
        if (user == null) {
            throw new UsernameNotFoundException(StatusCode.AUTHENTICATION_USER_NOT_FOUND.name());
        }
        return new UserInfo(user);
    }

    public UserDetails getUserDetailsById(String id) {
        log.warn("getUserDetailsById: {}", id);
        return Optional.ofNullable(getById(id)).map(UserInfo::new).orElse(null);
    }

}
