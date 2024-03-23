package buzz.xiaolan.plugins;

import lombok.extern.slf4j.Slf4j;
import org.apache.felix.framework.Felix;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.launch.Framework;
import org.osgi.framework.launch.FrameworkFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/5 22:46
 * @Description Application
 */
@Slf4j
@SpringBootApplication
public class Application {

    public static void main(String[] args) throws BundleException {
        SpringApplicationBuilder application = new SpringApplicationBuilder(Application.class);
        application.bannerMode(Banner.Mode.OFF);
        application.run(args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
           try {
               Thread.sleep(10000);
               OsgiFramework osgiFramework = OsgiFramework.getInstance();
               Felix felix = osgiFramework.getFelix();
               felix.start();
               BundleContext bundleContext = felix.getBundleContext();
               Bundle anotherBundle  = bundleContext.installBundle("file:\\project\\java\\xiaolan-framework\\bundle\\xiaolan-bundle-0.0.1-SNAPSHOT.jar");
               anotherBundle.start();

               ServiceReference<?> serviceReference = bundleContext.getServiceReference("buzz.xiaolan.bundle.HelloWorldService");

               Object service = bundleContext.getService(serviceReference);
               service.getClass().getMethod("sayHello").invoke(service);


               felix.stop();
               felix.waitForStop(0);
           }catch (Exception e){
              log.error(e.getMessage(), e);
           }
        };
    }

}
