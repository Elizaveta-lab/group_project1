package org.example.sorting;

public class SortByUsername extends Sort{

    public SortByUsername() {
        super(new SortByUsernameStrategy());
    }
}
