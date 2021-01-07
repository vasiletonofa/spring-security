package com.example.ssc1.config;

import com.example.ssc1.security.filters.CustomAuthenticationFilter;
import com.example.ssc1.security.providers.CustomOtpAuthenticationProvider;
import com.example.ssc1.security.providers.CustomUsernameAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationFilter customAuthenticationFilter;

    @Autowired
    private CustomUsernameAuthenticationProvider customUsernameAuthenticationProvider;

    @Autowired
    private CustomOtpAuthenticationProvider customOtpAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customUsernameAuthenticationProvider)
                .authenticationProvider(customOtpAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAt(customAuthenticationFilter, BasicAuthenticationFilter.class);

        http.authorizeRequests().anyRequest().permitAll();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
