package org.example.validation;

public class ValidationResult {
    private final boolean valid;
    private final String error;

    private ValidationResult(boolean valid, String error) {
        this.valid = valid;
        this.error = error;
    }

    public boolean isValid() {
        return valid;
    }

    public String getError() {
        return error;
    }

    public static ValidationResult ok() {
        return new ValidationResult(true, null);
    }

    public static ValidationResult fail(String error) {
        return new ValidationResult(false, error);
    }
}
