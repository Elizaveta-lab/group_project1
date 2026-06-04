package org.example.validation;

public class UserValidator {
    public ValidationResult validateUsername(String username) {

        if (isBlank(username)) {
            return ValidationResult.fail("Username cannot be empty");
        }

        if (containsWhitespace(username)) {
            return ValidationResult.fail("Username cannot contain spaces");
        }

        if (username.length() < 3) {
            return ValidationResult.fail("Username must be at least 3 characters");
        }

        if (username.length() > 20) {
            return ValidationResult.fail("Username must be at most 20 characters");
        }

        if (!username.matches("[A-Za-z0-9]+")) {
            return ValidationResult.fail("Username can contain only letters and digits");
        }

        return ValidationResult.ok();
    }

    public ValidationResult validatePassword(String password) {

        if (isBlank(password)) {
            return ValidationResult.fail("Password cannot be empty");
        }

        if (containsWhitespace(password)) {
            return ValidationResult.fail("Password cannot contain spaces");
        }

        if (password.length() < 8) {
            return ValidationResult.fail("Password must be at least 8 characters");
        }

        if (!password.matches(".*[A-Za-z].*") || !password.matches(".*[0-9].*")) {
            return ValidationResult.fail("Password must contain letters and numbers");
        }

        return ValidationResult.ok();
    }

    public ValidationResult validateEmail(String email) {

        if (isBlank(email)) {
            return ValidationResult.fail("Email cannot be empty");
        }

        if (containsWhitespace(email)) {
            return ValidationResult.fail("Email cannot contain spaces");
        }

        if (!email.contains("@") || !email.contains(".")) {
            return ValidationResult.fail("Email is invalid");
        }

        if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            return ValidationResult.fail("Email is invalid");
        }

        return ValidationResult.ok();
    }

    private boolean isBlank(String text) {
        return text == null || text.isBlank();
    }

    private boolean containsWhitespace(String text) {
        return text.chars().anyMatch(Character::isWhitespace);
    }
}
