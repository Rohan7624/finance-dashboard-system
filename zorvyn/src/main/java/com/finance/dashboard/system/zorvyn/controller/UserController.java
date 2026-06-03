package com.finance.dashboard.system.zorvyn.controller;

import com.finance.dashboard.system.zorvyn.entity.User;
import com.finance.dashboard.system.zorvyn.model.Role;
import com.finance.dashboard.system.zorvyn.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "User API", description = "User Management APIs")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Create new user (ADMIN only)")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @Operation(summary = "Get all users (ADMIN/ANALYST)")
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Update user role")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/role")
    public User updateRole(@PathVariable Long id,
                           @RequestParam Role newRole) {
        return userService.updateRole(id, newRole);
    }

    @Operation(summary = "Activate/Deactivate user")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/status")
    public User updateStatus(@PathVariable Long id,
                             @RequestParam boolean status) {
        return userService.updateStatus(id, status);
    }

    @Operation(summary = "Delete user")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }
}