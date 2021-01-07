package com.example.ssc1.security.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomUsernameAuthentication extends UsernamePasswordAuthenticationToken {

    public CustomUsernameAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public CustomUsernameAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
