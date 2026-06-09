package org.example.service;

import org.example.models.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadFromFile {

    public LoadUsersResult loadUsers(String fileName) {

        String filePath = fileName.endsWith(".txt")
                ? fileName
                : fileName + ".txt";

        List<User> users = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] parts = line.split(";");

                if (parts.length != 3) {
                    continue; // пропускаем битые строки
                }

                users.add(new User.Builder()
                        .setUsername(parts[0])
                        .setPassword(parts[1])
                        .setEmail(parts[2])
                        .build());
            }

        } catch (FileNotFoundException e) {
            return LoadUsersResult.error("File not found: " + filePath);

        } catch (IOException e) {
            return LoadUsersResult.error("Error reading file: " + e.getMessage());
        }

        return LoadUsersResult.success(users);
    }
}
