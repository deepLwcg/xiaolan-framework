package buzz.xiaolan.oauth.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/4/1
 * @Description OauthClientApplication
 */
@Slf4j
@SpringBootApplication
public class OauthClientApplication {
    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(OauthClientApplication.class);
        builder.bannerMode(Banner.Mode.OFF);
        builder.run(args);
    }
}
