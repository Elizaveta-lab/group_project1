package service;

import org.example.service.LoadFromFile;
import org.example.service.LoadUsersResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// тестирование сервиса загрузки из файлов
public class LoadFromFileTest {

    // успешная загрузка из файла
    private final LoadFromFile service = new LoadFromFile();

    @Test
    void shouldLoadValidUsersFromFile() {

        LoadUsersResult result = service.loadUsers("src/test/resources/data/valid-users.txt");

        assertTrue(result.isSuccess());
        assertEquals(3, result.getUsers().size());

        assertEquals("john", result.getUsers().get(0).getUsername());
        assertEquals("alice", result.getUsers().get(1).getUsername());
    }

    // Файл для загрузки данных не найден
    @Test
    void shouldReturnErrorWhenFileNotFound() {

        LoadUsersResult result = service.loadUsers("not-existing-file");

        assertFalse(result.isSuccess());
        assertTrue(result.getErrorMessage().contains("File not found"));
    }

    // Проверка на то, что файл найден и если есть битые строки то они игнорируются
    @Test
    void shouldSkipInvalidLines() {

        LoadUsersResult result = service.loadUsers("src/test/resources/data/invalid-users.txt");

        assertTrue(result.isSuccess());

        // только 1 нормальная строка
        assertEquals(1, result.getUsers().size());

        assertEquals("alice", result.getUsers().getFirst().getUsername());
    }
}
