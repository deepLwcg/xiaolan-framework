package buzz.xiaolan.security.config;

import buzz.xiaolan.security.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.authentication.session.ChangeSessionIdAuthenticationStrategy;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/26
 * @Description FilterConfig
 */
@Configuration
public class SecurityFilterConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ProviderManager providerManager(PasswordEncoder passwordEncoder, OauthUserDetailsService userDetailsService,
                                           GrantedAuthoritiesMapper authoritiesMapper) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setAuthoritiesMapper(authoritiesMapper);

        OauthSmsAuthenticationProvider oauthSmsAuthenticationProvider = new OauthSmsAuthenticationProvider(userDetailsService);

        return new ProviderManager(daoAuthenticationProvider, oauthSmsAuthenticationProvider);
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new OauthAuthenticationSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new OauthAuthenticationFailureHandler();
    }

    @Bean
    public CompositeSessionAuthenticationStrategy compositeSessionAuthenticationStrategy() {
        ChangeSessionIdAuthenticationStrategy changeSessionIdAuthenticationStrategy = new ChangeSessionIdAuthenticationStrategy();
        return new CompositeSessionAuthenticationStrategy(List.of(changeSessionIdAuthenticationStrategy));
    }

    @Bean
    public SecurityContextRepository securityContextRepository(OauthUserDetailsService userDetailsService,
                                                               AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource,
                                                               GrantedAuthoritiesMapper authoritiesMapper) {
        return new OauthSecurityContextRepository(userDetailsService, authenticationDetailsSource, authoritiesMapper);
    }

    @Bean
    public RememberMeServices rememberMeServices() {
        return new NullRememberMeServices();
    }

    @Bean
    public AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource() {
        return new WebAuthenticationDetailsSource();
    }

    @Bean
    public GrantedAuthoritiesMapper authoritiesMapper() {
        return new NullAuthoritiesMapper();
    }

    @Bean
    public OauthAuthenticationProcessingFilter oauthAuthenticationProcessingFilter(ProviderManager providerManager,
                                                                                   AuthenticationSuccessHandler authenticationSuccessHandler,
                                                                                   AuthenticationFailureHandler authenticationFailureHandler,
                                                                                   RememberMeServices rememberMeServices,
                                                                                   SecurityContextRepository securityContextRepository,
                                                                                   CompositeSessionAuthenticationStrategy compositeSessionAuthenticationStrategy,
                                                                                   AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource,
                                                                                   OauthSmsProcessor oauthSmsProcessor,
                                                                                   OauthEmailProcessor oauthEmailProcessor) {
        OauthAuthenticationProcessingFilter oauthAuthenticationProcessingFilter = new OauthAuthenticationProcessingFilter(new AntPathRequestMatcher("/login", "POST"),
                oauthSmsProcessor, oauthEmailProcessor);
        oauthAuthenticationProcessingFilter.setAuthenticationManager(providerManager);
        oauthAuthenticationProcessingFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        oauthAuthenticationProcessingFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        oauthAuthenticationProcessingFilter.setRememberMeServices(rememberMeServices);
        oauthAuthenticationProcessingFilter.setSecurityContextRepository(securityContextRepository);
        oauthAuthenticationProcessingFilter.setSessionAuthenticationStrategy(compositeSessionAuthenticationStrategy);
        oauthAuthenticationProcessingFilter.setAuthenticationDetailsSource(authenticationDetailsSource);
        return oauthAuthenticationProcessingFilter;
    }

}
