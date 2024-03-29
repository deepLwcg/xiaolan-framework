package buzz.xiaolan.security.dto;

import buzz.xiaolan.security.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/30
 * @Description RegisterUserVo
 */
@Data
public class RegisterUserVo {

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    public User toUser() {
        User user = new User();
        user.setNickname(this.getNickname());
        user.setEmail(this.getEmail());
        user.setPassword(this.getPassword());
        return user;
    }
}
