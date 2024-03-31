package buzz.xiaolan.security.config;

import buzz.xiaolan.security.security.filter.OauthAuthenticationProcessingFilter;
import buzz.xiaolan.security.security.filter.OauthSecurityContextHolderFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.ChangeSessionIdAuthenticationStrategy;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.security.web.context.SecurityContextRepository;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/25
 * @Description SecurityConfig
 */
@Configuration
// 这个注解必须加，开启Security
@EnableWebSecurity
//开启Security的权限注解
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private SecurityContextRepository securityContextRepository;
    private ChangeSessionIdAuthenticationStrategy changeSessionIdAuthenticationStrategy;
    private OauthAuthenticationProcessingFilter oauthAuthenticationProcessingFilter;
    private AccessDeniedHandler accessDeniedHandler;
    private AuthenticationEntryPoint authenticationEntryPoint;
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/favicon.ico","/druid/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().disable()
                .httpBasic().disable()
                .securityContext().securityContextRepository(securityContextRepository).disable()
                .rememberMe().disable()
                .sessionManagement()
                .sessionAuthenticationStrategy(changeSessionIdAuthenticationStrategy)
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**", "/api/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterAt(new OauthSecurityContextHolderFilter(securityContextRepository,authenticationFailureHandler), SecurityContextHolderFilter.class)
                .addFilterBefore(oauthAuthenticationProcessingFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
    }

    @Autowired
    public void setOauthAuthenticationProcessingFilter(OauthAuthenticationProcessingFilter oauthAuthenticationProcessingFilter) {
        this.oauthAuthenticationProcessingFilter = oauthAuthenticationProcessingFilter;
    }

    @Autowired
    public void setAccessDeniedHandler(AccessDeniedHandler accessDeniedHandler) {
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Autowired
    public void setSecurityContextRepository(SecurityContextRepository securityContextRepository) {
        this.securityContextRepository = securityContextRepository;
    }

    @Autowired
    public void setAuthenticationEntryPoint(AuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Autowired
    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Autowired
    public void setChangeSessionIdAuthenticationStrategy(ChangeSessionIdAuthenticationStrategy changeSessionIdAuthenticationStrategy) {
        this.changeSessionIdAuthenticationStrategy = changeSessionIdAuthenticationStrategy;
    }
}
