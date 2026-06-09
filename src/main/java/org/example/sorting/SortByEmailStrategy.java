package org.example.sorting;

import org.example.models.User;

import java.util.List;

public class SortByEmailStrategy implements SortStrategy{
    @Override
    public void sort(List<User> users) {
        int length = users.size();
        for (int gap = length/2; gap>0;gap/=2){
            for (int i=gap; i<length;i++){
                User temp = users.get(i);
                int j=i;
                while (j>=gap&& users.get(i - gap).getEmail().compareTo(temp.getEmail())>0){
                    users.set(j, users.get(j - gap));
                    j-=gap;
                }
                users.set(j, temp);
            }
        }
    }
}
