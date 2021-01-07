package com.example.ssc1.security.providers;

import com.example.ssc1.entities.Otp;
import com.example.ssc1.repositories.OtpRepository;
import com.example.ssc1.security.authentication.CustomOtpAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomOtpAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private OtpRepository otpRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String credentials = (String) authentication.getCredentials();

        Otp otp = otpRepository.findOtpByUsername(username).orElseThrow(() -> new UsernameNotFoundException("error"));

        if (otp.getOtp().equals(credentials)) {
            return new CustomOtpAuthentication(username, otp, List.of(() -> "read"));
        }

        throw new BadCredentialsException("error");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CustomOtpAuthentication.class.equals(aClass);
    }
}
