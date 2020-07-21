package com.javarush.task.task40.task4001;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* 
POST, а не GET
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        String m = "{message: \"task4001\", parentCommentId: null, replyToUserId: null}";
        solution.sendPost(new URL("https://javarush.ru/api/1.0/rest/discussions/discussion342259/comments"), m);
    }

    public void sendPost(URL url, String urlParameters) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        OutputStream os = connection.getOutputStream();
        os.write(urlParameters.getBytes());
        os.flush();

        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String responseLine;
        StringBuilder response = new StringBuilder();

        while ((responseLine = bufferedReader.readLine()) != null) {
            response.append(responseLine);
        }
        bufferedReader.close();

        System.out.println("Response: " + response.toString());
    }
}
