package com.finance.dashboard.system.zorvyn.security;


import com.finance.dashboard.system.zorvyn.model.Role;

public class AccessControl {

    public static void checkAdmin(Role role) {
        if (role != Role.ADMIN) {
            throw new RuntimeException("Access Denied: Admin only");
        }
    }

    public static void checkAnalystOrAdmin(Role role) {
        if (role != Role.ADMIN && role != Role.ANALYST) {
            throw new RuntimeException("Access Denied");
        }
    }
}