package com.javarush.task.task35.task3505;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* 
List to Map
*/
public class Solution {
    public static void main(String[] args) {
        List<ConvertableUser> users = new ArrayList<>();
        users.add(new ConvertableUser(1, "First User"));
        users.add(new ConvertableUser(2, "Second User"));
        users.add(new ConvertableUser(3, "Third User"));

        Map<Integer, ConvertableUser> newMap = ConvertableUtil.convert(users);
        System.out.println(newMap);
        //{236=ConvertableUser{id=1, name='Third User'},
        // 235=ConvertableUser{id=2, name='Second User'},
        // 234=ConvertableUser{id=3, name='First User'}}

        List<ConvertableBook> books = new ArrayList<>();
        books.add(new ConvertableBook("First Book"));
        books.add(new ConvertableBook("Second Book"));
        books.add(new ConvertableBook("Third Book"));

        Map<String, ConvertableBook> bookMap = ConvertableUtil.convert(books);
        System.out.println(bookMap);
        //{Third Book=ConvertableBook{name='Third Book'},
        // First Book=ConvertableBook{name='First Book'},
        // Second Book=ConvertableBook{name='Second Book'}}
    }
}
