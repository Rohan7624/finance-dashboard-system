package com.finance.dashboard.system.zorvyn.security.service;


import com.finance.dashboard.system.zorvyn.entity.User;
import com.finance.dashboard.system.zorvyn.repository.UserRepository;
import com.finance.dashboard.system.zorvyn.security.CustomUserDetails;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    public CustomUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> byEmail = repository.findByEmail(email.trim());
        //Optional<User> byEmail = repository.findByEmailIgnoreCase(email.trim());
        List<User> all = repository.findAll();
        all.forEach(u -> System.out.println(u.getEmail() + " (active: "));

        if (byEmail.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        User user = byEmail.get();
        return new CustomUserDetails(user);
    }
}