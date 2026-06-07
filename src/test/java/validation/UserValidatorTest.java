package validation;

import org.example.validation.UserValidator;
import org.example.validation.ValidationResult;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserValidatorTest {
    private final UserValidator validator = new UserValidator();

    @Nested
    public class usernameTest {
        @Test
        void validUsername() {
            ValidationResult result = validator.validateUsername("Alex123");

            assertTrue(result.isValid());
            assertNull(result.getError());
        }

        @Test
        void nullUsername() {
            ValidationResult result = validator.validateUsername(null);

            assertFalse(result.isValid());
            assertEquals("Username cannot be empty", result.getError());
        }

        @Test
        void blankUsername() {
            ValidationResult result = validator.validateUsername("   ");

            assertFalse(result.isValid());
            assertEquals("Username cannot be empty", result.getError());
        }

        @Test
        void usernameWithSpaces() {
            ValidationResult result = validator.validateUsername("Alex 123");

            assertFalse(result.isValid());
            assertEquals("Username cannot contain spaces", result.getError());
        }

        @Test
        void usernameTooShort() {
            ValidationResult result = validator.validateUsername("ab");

            assertFalse(result.isValid());
            assertEquals("Username must be at least 3 characters", result.getError());
        }

        @Test
        void usernameTooLong() {
            ValidationResult result = validator.validateUsername("abcdefghijklmnopqrstu");

            assertFalse(result.isValid());
            assertEquals("Username must be at most 20 characters", result.getError());
        }

        @Test
        void usernameContainsSpecialCharacters() {
            ValidationResult result = validator.validateUsername("Alex_123");

            assertFalse(result.isValid());
            assertEquals("Username can contain only letters and digits", result.getError());
        }
    }

    @Nested
    public class passwordTest{
        @Test
        void validPassword() {
            ValidationResult result = validator.validatePassword("Password123");

            assertTrue(result.isValid());
            assertNull(result.getError());
        }

        @Test
        void nullPassword() {
            ValidationResult result = validator.validatePassword(null);

            assertFalse(result.isValid());
            assertEquals("Password cannot be empty", result.getError());
        }

        @Test
        void blankPassword() {
            ValidationResult result = validator.validatePassword("   ");

            assertFalse(result.isValid());
            assertEquals("Password cannot be empty", result.getError());
        }

        @Test
        void passwordWithSpaces() {
            ValidationResult result = validator.validatePassword("Pass 1234");

            assertFalse(result.isValid());
            assertEquals("Password cannot contain spaces", result.getError());
        }

        @Test
        void passwordTooShort() {
            ValidationResult result = validator.validatePassword("Abc123");

            assertFalse(result.isValid());
            assertEquals("Password must be at least 8 characters", result.getError());
        }

        @Test
        void passwordWithoutLetters() {
            ValidationResult result = validator.validatePassword("12345678");

            assertFalse(result.isValid());
            assertEquals("Password must contain letters and numbers", result.getError());
        }

        @Test
        void passwordWithoutNumbers() {
            ValidationResult result = validator.validatePassword("Password");

            assertFalse(result.isValid());
            assertEquals("Password must contain letters and numbers", result.getError());
        }
    }

    @Nested
    public class emailTest {
        @Test
        void validEmail() {
            ValidationResult result = validator.validateEmail("alex@mail.com");

            assertTrue(result.isValid());
            assertNull(result.getError());
        }

        @Test
        void nullEmail() {
            ValidationResult result = validator.validateEmail(null);

            assertFalse(result.isValid());
            assertEquals("Email cannot be empty", result.getError());
        }

        @Test
        void blankEmail() {
            ValidationResult result = validator.validateEmail("   ");

            assertFalse(result.isValid());
            assertEquals("Email cannot be empty", result.getError());
        }

        @Test
        void emailWithSpaces() {
            ValidationResult result = validator.validateEmail("alex @mail.com");

            assertFalse(result.isValid());
            assertEquals("Email cannot contain spaces", result.getError());
        }

        @Test
        void emailWithoutAt() {
            ValidationResult result = validator.validateEmail("alexmail.com");

            assertFalse(result.isValid());
            assertEquals("Email is invalid", result.getError());
        }

        @Test
        void emailWithoutDot() {
            ValidationResult result = validator.validateEmail("alex@mailcom");

            assertFalse(result.isValid());
            assertEquals("Email is invalid", result.getError());
        }

        @Test
        void emailWithInvalidFormat() {
            ValidationResult result = validator.validateEmail("alex@@mail.com");

            assertFalse(result.isValid());
            assertEquals("Email is invalid", result.getError());
        }
    }


}
