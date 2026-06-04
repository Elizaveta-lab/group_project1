package org.example.UI;

import org.example.models.User;
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

    public void start(){
        runUILoop();
    }

    // основной цикл UI для взаимодействия с пользователем
    private void runUILoop() {
        System.out.println("Welcome to the sorting program.");

        int choice;

        while (true) {
            System.out.println("""
                \nMain menu

                1. Display users
                2. Add new user
                3. Sorting
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
                    System.out.println("Sorting?");
                    break;
                case 0:
                    System.out.println("Exit");
                    return;
                default:
                    System.out.println("?");
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
    private String readValidInput(String prompt,
                                  Function<String, ValidationResult> validatorFunction) {

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
}
