package com.example.ssc1.security.providers;

import com.example.ssc1.security.authentication.CustomUsernameAuthentication;
import com.example.ssc1.service.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomUsernameAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JpaUserDetailsService jpaUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        UserDetails userDetails = jpaUserDetailsService.loadUserByUsername(username);
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            return new CustomUsernameAuthentication(username, password, userDetails.getAuthorities());
        }

        throw new BadCredentialsException("error");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CustomUsernameAuthentication.class.equals(aClass);
    }
}
