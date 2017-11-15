package com.lllockkk.security.browser;

import com.lllockkk.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * Created by L on 2017/11/14.
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
//    @Qualifier("myAuthenticationSuccessHandler")
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
//    @Qualifier("myAuthenticationFailureHanlder")
    private AuthenticationFailureHandler authenticationFailureHanlder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
            .loginPage("/authentication/require")
            .loginProcessingUrl("/authentication/form")
            .successHandler(authenticationSuccessHandler)
            .failureHandler(authenticationFailureHanlder)
            .and()
            .authorizeRequests()
            .antMatchers("/authentication/require",
                    securityProperties.getBrowser().getLoginPage(),
                    "/code/image").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
