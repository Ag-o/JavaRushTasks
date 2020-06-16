package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* 
Знакомство с properties
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

//    static {
//        properties.put("1", "one");
//        properties.put("2", "two");
//    }

    public void fillInPropertiesMap() {
        //implement this method - реализуйте этот метод
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file;
        try {
            file = reader.readLine();
            FileInputStream fileInputStream = new FileInputStream(file);
            load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save(OutputStream outputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties proper = new Properties();
        //prop.putAll(properties);

        for (Map.Entry<String, String> entry : properties.entrySet()) {
            proper.setProperty(entry.getKey(), entry.getValue());
        }
        proper.store(outputStream, "---------");
    }

    public void load(InputStream inputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties prop = new Properties();
        prop.load(inputStream);
        prop.forEach((a, b) -> properties.put((String) a, (String) b));
//        for (String name : prop.stringPropertyNames()) {
//            properties.put(name, prop.getProperty(name));
//        }
    }

    public static void main(String[] args) throws Exception {

    }
}
