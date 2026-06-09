package org.example.sorting;

import org.example.models.User;

import java.util.List;

public class SortByEmailStrategy implements SortStrategy{
    @Override
    public void sort(List<User> users) {
        int length = users.size();
        int gap = 1;
        User temp;

        while (gap<= users.size() / 3){
            gap = gap* 3 + 1;
        }

        while (gap>0){
            for (int i = gap; i<length;i++){
                temp = users.get(i);
                int j = i;
                while (j>gap-1&&users.get(j - gap).getEmail().compareTo(temp.getEmail())>0){
                    users.set(j, users.get(j - gap));
                    j -= gap;
                }
                users.set(j, temp);
            }
            gap = (gap - 1) / 3;
        }
    }
}
