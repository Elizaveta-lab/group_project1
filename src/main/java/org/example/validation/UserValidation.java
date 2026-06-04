package org.example.validation;

import java.util.HashMap;
import java.util.Map;

public class UserValidation {
    private final static Map<String, String> invalidData = new HashMap<>();

    public static void getInvalidData() {
        invalidData.forEach((key, value) ->
                System.out.println("Неправильные поля или имеются пустые строки:\n" + key + ": " + value));
    }

    public static boolean validate(String username, String password, String email) {
        invalidData.clear();
        boolean isValid = true;

        if (!isValidUsername(username)) {
            invalidData.put("Username", username);
            isValid = false;
        }

        if (!isValidPassword(password)) {
            invalidData.put("Password", password);
            isValid = false;
        }

        if (!isValidEmail(email)) {
            invalidData.put("Email", email);
            isValid = false;
        }

        return isValid;
    }

    private static boolean isValidUsername(String username) {
        return isNotNullOrEmpty(username) && username.matches("^[a-zA-Z0-9_]{3,12}$");
    }

    private static boolean isValidPassword(String password) {
        return isNotNullOrEmpty(password) && password.matches("^[a-zA-Z0-9]{6,16}$");
    }

    private static boolean isValidEmail(String email) {
        return isNotNullOrEmpty(email) && email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(com|ru|net|org)$");
    }

    private static boolean isNotNullOrEmpty(String string) {
        return string != null && !string.trim().isEmpty();
    }
}
