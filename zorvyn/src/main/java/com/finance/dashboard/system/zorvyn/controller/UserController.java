package com.finance.dashboard.system.zorvyn.controller;


import com.finance.dashboard.system.zorvyn.entity.User;
import com.finance.dashboard.system.zorvyn.model.Role;
import com.finance.dashboard.system.zorvyn.security.AccessControl;
import com.finance.dashboard.system.zorvyn.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ADMIN only
    @PostMapping
    public User createUser(@RequestBody User user, @RequestParam Role role) {
        AccessControl.checkAdmin(role);
        return userService.createUser(user);
    }

    // ANALYST + ADMIN
    @GetMapping
    public List<User> getUsers(@RequestParam Role role) {
        AccessControl.checkAnalystOrAdmin(role);
        return userService.getAllUsers();
    }

    // ADMIN only
    @PutMapping("/{id}/role")
    public User updateRole(@PathVariable Long id,
                           @RequestParam Role newRole,
                           @RequestParam Role currentUserRole) {
        AccessControl.checkAdmin(currentUserRole);
        return userService.updateUserRole(id, newRole);
    }

    // ADMIN only
    @DeleteMapping("/{id}")
    public String deactivateUser(@PathVariable Long id,
                                 @RequestParam Role role) {
        AccessControl.checkAdmin(role);
        userService.deactivateUser(id);
        return "User deactivated";
    }
}