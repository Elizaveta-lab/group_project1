package org.example.validation;

public class UserValidation {

    public static boolean validate(String username, String password, String email) {
        boolean isValid = true;

        if (!isValidUsername(username)) {
            isValid = false;
        }

        if (!isValidPassword(password)) {
            isValid = false;
        }

        if (!isValidEmail(email)) {
            isValid = false;
        }

        return isValid;
    }

    private static boolean isValidUsername(String username) {
        return isNotNullOrEmpty(username) && username.matches("^[A-Za-z0-9_-]{4,16}$");
    }

    private static boolean isValidPassword(String password) {
        return isNotNullOrEmpty(password) && password.matches("^[A-Za-z0-9!.]{6,20}$");
    }

    private static boolean isValidEmail(String email) {
        return isNotNullOrEmpty(email) && email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    private static boolean isNotNullOrEmpty(String string) {
        return string != null && !string.trim().isEmpty();
    }
}
