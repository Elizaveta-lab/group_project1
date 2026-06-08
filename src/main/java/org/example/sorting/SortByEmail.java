package org.example.sorting;

public class SortByEmail extends Sort{
    public SortByEmail() {
        super(new SortByEmailStrategy());
    }
}
