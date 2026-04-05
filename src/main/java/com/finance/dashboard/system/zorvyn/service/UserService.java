package com.finance.dashboard.system.zorvyn.service;


import com.finance.dashboard.system.zorvyn.entity.User;
import com.finance.dashboard.system.zorvyn.model.Role;
import com.finance.dashboard.system.zorvyn.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder= passwordEncoder;
    }

    // CREATE USER
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // GET ALL USERS
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // UPDATE ROLE
    public User updateRole(Long id, Role role) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Record not found"));
        user.setRole(role);
        return userRepository.save(user);
    }

    // ACTIVATE / DEACTIVATE USER
    public User updateStatus(Long id, boolean status) {
        User user = userRepository.findById(id).orElseThrow();
        user.setActive(status);
        return userRepository.save(user);
    }

    // DELETE USER
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}