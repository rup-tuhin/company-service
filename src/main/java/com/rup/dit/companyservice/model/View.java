package com.rup.dit.companyservice.model;

import com.rup.dit.companyservice.config.AppSecurityConfig.Role;

import java.util.HashMap;
import java.util.Map;

public class View {

    public static final Map<Role, Class> MAPPING = new HashMap<>();

    static {
        MAPPING.put(Role.ROLE_ADMIN, Admin.class);
        MAPPING.put(Role.ROLE_USER, User.class);
    }

    public static class User {

    }

    public static class Admin extends User {

    }
}