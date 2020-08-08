package com.javarush.task.task23.task2306;

/* 
Inner 2
*/
public class Solution {
    private String country;
    private String city;

    public Solution(String country, String city) {
        this.country = country;
        this.city = city;
    }

    public String getUserDescription(String name) {
        return getTrickyUser(name).getDescription();
    }

    public SuperUser getTrickyUser(String name) {
        return new SuperUser(name);
    }

    private class SuperUser {
        private String name;

        public SuperUser(String name) {
            this.name = name;
        }

        public String getDescription() {
            return String.format("My name is %s. I'm from %s in %s.", this.name, Solution.this.city, Solution.this.country);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution("the United States", "Seattle");
        System.out.println(solution.getTrickyUser("George").getDescription());
        System.out.println(solution.getUserDescription("George"));
    }
}
