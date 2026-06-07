package org.example.service;

import org.example.models.User;

import java.util.List;

public class LoadUsersResult {
    private final List<User> users;
    private final boolean success;
    private final String errorMessage;

    private LoadUsersResult(List<User> users, boolean success, String errorMessage) {
        this.users = users;
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public static LoadUsersResult success(List<User> users) {
        return new LoadUsersResult(users, true, null);
    }

    public static LoadUsersResult error(String message) {
        return new LoadUsersResult(List.of(), false, message);
    }

    public List<User> getUsers() {
        return users;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
