package com.example.project_transition.enumeration;


public enum Role {

    ROLE_USER("user"),
    ROLE_ADMIN("admin");

    private String[] authorities;

    Role(String... authorities) {
        this.authorities = authorities;
    }

    public String[] getAuthorities() {
        return authorities;
    }

}
