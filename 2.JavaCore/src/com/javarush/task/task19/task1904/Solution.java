package com.javarush.task.task19.task1904;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        String name = "Иванов Иван Иванович 13 09 1993";

        PersonScannerAdapter adapter = new PersonScannerAdapter(new Scanner(name));

        System.out.println(adapter.read());
        System.out.println(new PersonScannerAdapter(new Scanner(new File("C:\\JavaRush_my_project\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task19\\task1904\\text.txt"), "windows-1251")).read());

    }

    public static class PersonScannerAdapter implements PersonScanner {
        private final Scanner fileScanner;

        public PersonScannerAdapter(Scanner fileScanner) {
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException {
            String line = fileScanner.nextLine();
            String[] arr = line.split("\\s", 4);

            SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
            Date date = null;
            try {
                date = sdf.parse(arr[3]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //Date dob = sdf.parse(String.format( "%s %s %s", date, month, year));

            return new Person(arr[1], arr[2], arr[0], date);
        }

        @Override
        public void close() throws IOException {
            fileScanner.close();
        }
    }
}
