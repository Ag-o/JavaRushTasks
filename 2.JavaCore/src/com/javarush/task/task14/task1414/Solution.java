package com.javarush.task.task14.task1414;

/* 
MovieFactory
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            Movie movie;
            String s = reader.readLine();

            switch (s) {
                case "soapOpera":
                    movie = MovieFactory.getMovie(s);
                    System.out.println(movie.getClass().getSimpleName());
                    break;
                case "cartoon":
                    movie = MovieFactory.getMovie(s);
                    System.out.println(movie.getClass().getSimpleName());
                    break;
                case "thriller":
                    movie = MovieFactory.getMovie(s);
                    System.out.println(movie.getClass().getSimpleName());
                    break;
                default:
                    MovieFactory.getMovie(s);
                    reader.close();
                    break;
            }
        }
    }

    static class MovieFactory {

        static Movie getMovie(String key) {
            Movie movie = null;

            if ("soapOpera".equals(key)) {
                movie = new SoapOpera();
            } else if ("cartoon".equals(key)) {
                movie = new Cartoon();
            } else if ("thriller".equals(key)) {
                movie = new Thriller();
            }
            return movie;
        }
    }

    static abstract class Movie {
    }

    static class SoapOpera extends Movie {
    }

    public static class Cartoon extends Movie {

    }

    public static class Thriller extends Movie {

    }
}