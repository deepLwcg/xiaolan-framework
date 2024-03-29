package buzz.xiaolan.security.dto;

import lombok.Data;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/29
 * @Description AccessToken
 */

@Data(staticConstructor = "of")
public class AccessToken {

    private final String accessToken;

    private final String refreshToken;

    private final Long expiresIn;



}
