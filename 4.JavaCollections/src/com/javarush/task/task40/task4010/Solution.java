package com.javarush.task.task40.task4010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/* 
Коды ошибок
*/

public class Solution {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://jsonplaceholder.typicode.com/posts/1");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0");

            if (urlConnection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + urlConnection.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((urlConnection.getInputStream())));

            System.out.println("Server output .... \n");
            String output;
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            urlConnection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
