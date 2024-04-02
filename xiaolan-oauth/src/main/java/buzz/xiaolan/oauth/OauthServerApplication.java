package buzz.xiaolan.oauth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/31
 * @Description OauthServerApplication
 */
@Slf4j
@SpringBootApplication
public class OauthServerApplication {
    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(OauthServerApplication.class);
        builder.bannerMode(Banner.Mode.OFF);
        builder.run(args);
    }
}
