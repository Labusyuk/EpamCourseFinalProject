package com.labus.bankpayments.entity;

public class User extends Entity {
private String login;
private String password;
private UserRole role;
private String name_first;
private String name_last;

    public User(int id, String login, String password, UserRole role, String name_first, String name_last) {
        super(id);
        this.login = login;
        this.password = password;
        this.role = role;
        this.name_first = name_first;
        this.name_last = name_last;
    }
    public User(){

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getName_first() {
        return name_first;
    }

    public void setName_first(String name_first) {
        this.name_first = name_first;
    }

    public String getName_last() {
        return name_last;
    }

    public void setName_last(String name_last) {
        this.name_last = name_last;
    }
}
