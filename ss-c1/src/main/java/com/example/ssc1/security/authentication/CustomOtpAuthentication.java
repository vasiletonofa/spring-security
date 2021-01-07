package com.example.ssc1.security.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomOtpAuthentication extends UsernamePasswordAuthenticationToken {

    public CustomOtpAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public CustomOtpAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

}
