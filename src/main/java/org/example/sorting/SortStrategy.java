package org.example.sorting;

import org.example.models.User;

import java.util.List;

public interface SortStrategy {
    public void sort(List<User> users);
}
