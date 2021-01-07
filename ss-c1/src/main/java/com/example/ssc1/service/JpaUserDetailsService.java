package com.example.ssc1.service;

import com.example.ssc1.entities.User;
import com.example.ssc1.repositories.UserRepository;
import com.example.ssc1.security.model.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        var o = userRepository.findUserByUsername(s);

        User u = o.orElseThrow(() -> new UsernameNotFoundException("Error"));

        return new SecurityUser(u);
    }
}
