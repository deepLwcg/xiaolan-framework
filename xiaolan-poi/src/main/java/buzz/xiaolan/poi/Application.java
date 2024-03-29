package buzz.xiaolan.poi;

import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/24
 * @Description Application
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        disableSpringCloudBootstrap();
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
        builder.web(WebApplicationType.NONE);
        builder.bannerMode(Banner.Mode.OFF);
        builder.run(args);
    }
    private static void disableSpringCloudBootstrap() {
        // 禁用bootstrap
        System.setProperty("spring.cloud.bootstrap.enabled", "false");
    }
}
