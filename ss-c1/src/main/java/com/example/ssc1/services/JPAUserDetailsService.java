package com.example.ssc1.services;

import com.example.ssc1.entities.User;
import com.example.ssc1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class JPAUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(s);
        User user1 = user.orElseThrow(() -> new UsernameNotFoundException("Error"));
        return new SecurityUser(user1);
    }
}
