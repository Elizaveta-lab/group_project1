package org.example.UI;

import org.example.models.User;
import org.example.service.LoadFromFile;
import org.example.service.LoadUsersResult;
import org.example.validation.UserValidator;
import org.example.validation.ValidationResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class ConsoleUI {

    private final List<User> users = new ArrayList<>();
    private final UserValidator validator = new UserValidator();
    private final Scanner scanner = new Scanner(System.in);
    private final LoadFromFile loadFromFile = new LoadFromFile();

    public void start() {
        runUILoop();
    }

    // основной цикл UI для взаимодействия с пользователем
    private void runUILoop() {

        System.out.println("Welcome to the sorting program.");

        int choice;

        while (true) {
            System.out.println("""
                    
                    Main menu
                    1. Display users
                    2. Add new user
                    3. Load users from file
                    4. Generate random users
                    5. Sorting
                    0. Exit
                    """);

            String input = scanner.nextLine();

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number!");
                continue;
            }

            switch (choice) {
                case 1:
                    if (users.isEmpty()) {
                        System.out.println("No users found.");
                    } else {
                        System.out.println("Users list:");
                        for (User user : users) {
                            System.out.println("--------------------");
                            System.out.println(user);
                        }
                    }
                    break;

                case 2:
                    addUser();
                    break;

                case 3:
                    loadFromFileMenu();
                    break;

                case 4:
                    // generateRandomUsers();
                    break;

                case 5:
                    showSortingMenu();
                    break;

                case 0:
                    System.out.println("Exit");
                    return;

                default:
                    System.out.println("Invalid option");
            }
        }
    }

    // Метод добавления нового пользователя
    private void addUser() {

        String username = readValidInput("Enter username", validator::validateUsername);
        if (username == null) return;

        String password = readValidInput("Enter password", validator::validatePassword);
        if (password == null) return;

        String email = readValidInput("Enter email", validator::validateEmail);
        if (email == null) return;

        users.add(new User.Builder()
                .setUsername(username)
                .setPassword(password)
                .setEmail(email)
                .build());

        System.out.println("User added successfully.");
    }

    // вспомогательный метод для добавления нового пользователя
    private String readValidInput(String prompt, Function<String, ValidationResult> validatorFunction) {

        while (true) {
            System.out.print(prompt + " (or type 'cancel') ");
            String value = scanner.nextLine();

            if (value.equalsIgnoreCase("cancel")) {
                return null;
            }

            ValidationResult result = validatorFunction.apply(value);

            if (result.isValid()) {
                return value;
            }

            System.out.println(result.getError());
        }
    }

    // метод отображения меню сортировки
    private void showSortingMenu() {

        while (true) {
            System.out.println("""
                    
                    Sort users by:
                    1. Username
                    2. Password
                    3. Email
                    0. Back
                    """);

            String input = scanner.nextLine();

            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number!");
                continue;
            }

            switch (choice) {
                case 1:
                    // sortByUsername();
                    System.out.println("Sorting by username completed successfully.");
                    break;

                case 2:
                    // sortByPassword();
                    System.out.println("Sorting by password completed successfully.");
                    break;

                case 3:
                    // sortByEmail();
                    System.out.println("Sorting by email completed successfully.");
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Invalid option");
            }
        }
    }

    // вспомогательный метод для указания пути к файлу.
    // файл с юзерами находится в папке data, в консоли надо ввести data/users для его загрузки
    private void loadFromFileMenu() {
        System.out.println("Load users from file.");
        while (true) {
            System.out.println("Enter file name or 0 to back.");

            String fileName = scanner.nextLine();

            if ("0".equals(fileName)) {
                return;
            }

            LoadUsersResult result = loadFromFile.loadUsers(fileName);

            if (!result.isSuccess()) {
                System.out.println("Error: " + result.getErrorMessage());
                continue;
            }

            users.addAll(result.getUsers());

            System.out.println("Loaded " + result.getUsers().size() + " users.");
            break;
        }
    }
}
