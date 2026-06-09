package org.example.service;

import org.example.models.User;
import org.example.validation.UserValidation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomUserGeneration {
    // Условный рандом можно также вынести в отдельный файл с различными именами \ распарсить их и подставлять сюда
    private static final String[] NAMES = {"andrew", "michael", "valeriy", "maria", "ann", "yu", "john", "anastasia"};
    private static final String[] NICKS = {"dev", "coder", "gamer", "artist", "boss", "tester", "pro"};
    private static final String[] DOMAINS = {"gmail.com", "yandex.ru", "yahoo.com", "mail.ru", "hal.ac.jp", "org.ru"};

    private final Random random = new Random();

    public List<User> generateRandomUsers(int count) {
        List<User> genUsers = new ArrayList<>();

        while (genUsers.size() < count) {
            User randomUser = generateRandomUser();
            String username = randomUser.getUsername();
            String password = randomUser.getPassword();
            String email = randomUser.getEmail();

            if (UserValidation.validate(username, password, email)) {
                genUsers.add(new User.Builder().setUsername(username)
                        .setPassword(password)
                        .setEmail(email)
                        .build());
            }
        }

        return genUsers;
    }

    private User generateRandomUser() {
        String firstName = NAMES[random.nextInt(NAMES.length)];
        String lastName = NICKS[random.nextInt(NICKS.length)];
        int number = random.nextInt(1000);

        String username = firstName + "_" + lastName + "_" + number;
        String password = "Gen" + firstName + number + "!";

        String domain = DOMAINS[random.nextInt(DOMAINS.length)];
        String email = username + "@" + domain;

        return new User.Builder()
                .setUsername(username)
                .setPassword(password)
                .setEmail(email)
                .build();
    }
}
