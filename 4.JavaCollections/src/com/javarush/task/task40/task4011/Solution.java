package com.javarush.task.task40.task4011;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/* 
Свойства URL
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        decodeURLString("https://www.amrood.com/index.htm?language=en#j2se");
    }

    public static void decodeURLString(String s) throws MalformedURLException {
        try {
            URL url = new URL(s);

            String builder = "protocol- " + url.getProtocol() + "\n" +
                    "authority- " + url.getAuthority() + "\n" +
                    "file- " + url.getFile() + "\n" +
                    "host- " + url.getHost() + "\n" +
                    "path- " + url.getPath() + "\n" +
                    "port- " + url.getPort() + "\n" +
                    "default port- " + url.getDefaultPort() + "\n" +
                    "query- " + url.getQuery() + "\n" +
                    "ref- " + url.getRef();
            System.out.println(builder);

        } catch (MalformedURLException e) {
            System.out.println("Parameter " + s + " is not a valid URL.");
        }
    }
}

