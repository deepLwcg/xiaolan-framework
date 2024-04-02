package buzz.xiaolan.oauth.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/4/1
 * @Description SecurityConfig
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated());
        http.oauth2Login(Customizer.withDefaults());
        return http.build();
    }
}
