package com.cd.acceptance.examples.accounting;

public class User {
    private String name;
    private String password;
    private UserRole role;

    public User(String name, String password, UserRole role) {

        this.name = name;
        this.password = password;
        this.role = role;
    }

    public boolean validate(String pasword) {
        return this.password.equals(pasword);
    }

    public boolean hasPermission(UserRole role) {
        return this.role == role;
    }

    public String getName() {
        return name;
    }
}
