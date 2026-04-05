package com.finance.dashboard.system.zorvyn.repository;


import com.finance.dashboard.system.zorvyn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}