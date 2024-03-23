package buzz.xiaolan.floatingbox;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/10 02:39
 * @Description BoxApplication
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplicationBuilder application = new SpringApplicationBuilder(Application.class);
        application.bannerMode(Banner.Mode.OFF);
        application.run(args);
    }

}
