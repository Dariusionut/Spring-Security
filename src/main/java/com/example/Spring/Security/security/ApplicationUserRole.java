package com.example.Spring.Security.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.example.Spring.Security.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    CLIENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(CAR_READ, CAR_WRITE, CLIENT_READ, CLIENT_WRITE)),
    ADMINTRAINEE(Sets.newHashSet(CAR_READ, CLIENT_READ));

    private final Set <ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}
