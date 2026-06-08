package org.example.sorting;

import org.example.models.User;

import java.util.List;

public class Sort {
    private final SortStrategy sortStrategy;

    public Sort(SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    public void sort(List<User> users){
        sortStrategy.sort(users);
    }
}
