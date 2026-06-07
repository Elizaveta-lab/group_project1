package validation;

import org.example.validation.UserValidator;
import org.example.validation.ValidationResult;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Тестирование имени пользователя, пароля и почты на валидность
public class UserValidatorTest {
    private final UserValidator validator = new UserValidator();

    // тестирование валидности имени пользователя
    @Nested
    public class usernameTest {

        // проверка на валидное имя
        @Test
        void validUsername() {
            ValidationResult result = validator.validateUsername("Alex123");

            assertTrue(result.isValid());
            assertNull(result.getError());
        }

        // проверка на null
        @Test
        void nullUsername() {
            ValidationResult result = validator.validateUsername(null);

            assertFalse(result.isValid());
            assertEquals("Username cannot be empty", result.getError());
        }

        // проверка на пустую строку
        @Test
        void blankUsername() {
            ValidationResult result = validator.validateUsername("   ");

            assertFalse(result.isValid());
            assertEquals("Username cannot be empty", result.getError());
        }

        // проверка строки на содержание пробелов
        @Test
        void usernameWithSpaces() {
            ValidationResult result = validator.validateUsername("Alex 123");

            assertFalse(result.isValid());
            assertEquals("Username cannot contain spaces", result.getError());
        }

        // проверка строки на минимальную длину
        @Test
        void usernameTooShort() {
            ValidationResult result = validator.validateUsername("ab");

            assertFalse(result.isValid());
            assertEquals("Username must be at least 3 characters", result.getError());
        }

        // проверка строки на максимальную длину
        @Test
        void usernameTooLong() {
            ValidationResult result = validator.validateUsername("abcdefghijklmnopqrstu");

            assertFalse(result.isValid());
            assertEquals("Username must be at most 20 characters", result.getError());
        }

        // Проверка строки на допустимые символы
        @Test
        void usernameContainsSpecialCharacters() {
            ValidationResult result = validator.validateUsername("Alex_123");

            assertFalse(result.isValid());
            assertEquals("Username can contain only letters and digits", result.getError());
        }
    }

    // тестирование валидности пароля
    @Nested
    public class passwordTest{

        // проверка валидного пароля
        @Test
        void validPassword() {
            ValidationResult result = validator.validatePassword("Password123");

            assertTrue(result.isValid());
            assertNull(result.getError());
        }

        // проверка строки на null
        @Test
        void nullPassword() {
            ValidationResult result = validator.validatePassword(null);

            assertFalse(result.isValid());
            assertEquals("Password cannot be empty", result.getError());
        }

        // проверка на пустую строку
        @Test
        void blankPassword() {
            ValidationResult result = validator.validatePassword("   ");

            assertFalse(result.isValid());
            assertEquals("Password cannot be empty", result.getError());
        }

        // проверка строки на наличие пробелов
        @Test
        void passwordWithSpaces() {
            ValidationResult result = validator.validatePassword("Pass 1234");

            assertFalse(result.isValid());
            assertEquals("Password cannot contain spaces", result.getError());
        }

        // проверка строки на минимальную длину
        @Test
        void passwordTooShort() {
            ValidationResult result = validator.validatePassword("Abc123");

            assertFalse(result.isValid());
            assertEquals("Password must be at least 8 characters", result.getError());
        }

        // проверка строки на содержание букв
        @Test
        void passwordWithoutLetters() {
            ValidationResult result = validator.validatePassword("12345678");

            assertFalse(result.isValid());
            assertEquals("Password must contain letters and numbers", result.getError());
        }

        // проверка строки на содержание цифр
        @Test
        void passwordWithoutNumbers() {
            ValidationResult result = validator.validatePassword("Password");

            assertFalse(result.isValid());
            assertEquals("Password must contain letters and numbers", result.getError());
        }
    }

    // тестирование валидности почты
    @Nested
    public class emailTest {

        // проверка валидной почты
        @Test
        void validEmail() {
            ValidationResult result = validator.validateEmail("alex@mail.com");

            assertTrue(result.isValid());
            assertNull(result.getError());
        }

        // проверка строки на null
        @Test
        void nullEmail() {
            ValidationResult result = validator.validateEmail(null);

            assertFalse(result.isValid());
            assertEquals("Email cannot be empty", result.getError());
        }

        // проверка пустой строки
        @Test
        void blankEmail() {
            ValidationResult result = validator.validateEmail("   ");

            assertFalse(result.isValid());
            assertEquals("Email cannot be empty", result.getError());
        }

        // проверка строки на наличие пробелов
        @Test
        void emailWithSpaces() {
            ValidationResult result = validator.validateEmail("alex @mail.com");

            assertFalse(result.isValid());
            assertEquals("Email cannot contain spaces", result.getError());
        }

        // проверка строки на наличие знака @
        @Test
        void emailWithoutAt() {
            ValidationResult result = validator.validateEmail("alexmail.com");

            assertFalse(result.isValid());
            assertEquals("Email is invalid", result.getError());
        }

        // проверка строки на наличие доменной зоны
        @Test
        void emailWithoutDot() {
            ValidationResult result = validator.validateEmail("alex@mailcom");

            assertFalse(result.isValid());
            assertEquals("Email is invalid", result.getError());
        }

        // проверка строки на неправильный формат
        @Test
        void emailWithInvalidFormat() {
            ValidationResult result = validator.validateEmail("alex@@mail.com");

            assertFalse(result.isValid());
            assertEquals("Email is invalid", result.getError());
        }
    }
}
