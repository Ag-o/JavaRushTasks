package com.javarush.task.task20.task2002;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Читаем и пишем в файл: JavaRush
*/
public class Solution {
    public static void main(String[] args) {
        String text = "C:\\JavaRush_my_project\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task20\\task2002\\text.txt";
        try {
            OutputStream outputStream = new FileOutputStream(text);
            InputStream inputStream = new FileInputStream(text);

            JavaRush javaRush = new JavaRush();

            User user = new User();
            user.setFirstName("First");
            user.setLastName("Last");
            user.setBirthDate(new Date(1508944512233L));
            user.setMale(true);
            user.setCountry(User.Country.OTHER);

            User user1 = new User();
            user1.setFirstName("Vasa");
            user1.setLastName("Petrov");
            user1.setBirthDate(new Date(1508944516163L));
            user1.setMale(true);
            user1.setCountry(User.Country.RUSSIA);

            javaRush.users.add(user);
            javaRush.users.add(user1);

            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);

            outputStream.close();
            inputStream.close();

            javaRush.users.forEach(System.out::println);
            loadedObject.users.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Oops, something is wrong with my file");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Oops, something is wrong with the save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            PrintWriter pw = new PrintWriter(outputStream);
            if (!users.isEmpty()) {
                for (User user : users) {
                    if (user.getFirstName() != null) {
                        pw.println(user.getFirstName());
                    }
                    if (user.getLastName() != null) {
                        pw.println(user.getLastName());
                    }
                    if (user.getBirthDate() != null) {
                        pw.println(user.getBirthDate().getTime());
                    }
                    pw.println(user.isMale());
                    if (user.getCountry() != null) {
                        pw.println(user.getCountry());
                    }
                }
                pw.flush();
            }
            pw.close();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while (reader.ready()) {
                User user = new User();
                user.setFirstName(reader.readLine());
                user.setLastName(reader.readLine());
                user.setBirthDate(new Date(Long.parseLong(reader.readLine())));
                user.setMale(Boolean.parseBoolean(reader.readLine()));
                String country = reader.readLine();
                if (country.equals("UKRAINE")) {
                    user.setCountry(User.Country.UKRAINE);
                }
                if (country.equals("RUSSIA")) {
                    user.setCountry(User.Country.RUSSIA);
                }
                if (country.equals("OTHER")) {
                    user.setCountry(User.Country.OTHER);
                }

                int sov = 0;
                for (User u : users) {
                    if (user.equals(u)) {
                        sov++;
                    }
                }
                if (sov == 0) {
                    this.users.add(user);
                }
            }
            reader.close();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
