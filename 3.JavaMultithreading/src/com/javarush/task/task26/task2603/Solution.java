package com.javarush.task.task26.task2603;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
Убежденному убеждать других не трудно
*/
public class Solution {

    private int one;

    public Solution(int one) {
        this.one = one;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "one=" + one +
                '}';
    }

    public static void main(String[] args) {
        Comparator comparatorOne = (Comparator<Solution>) (o1, o2) -> o1.one - o2.one;
        Comparator comparatorTwo = (Comparator<Solution>) (o1, o2) -> o2.one - o1.one;

        List<Solution> list = new ArrayList<>();
        list.add(new Solution(1));
        list.add(new Solution(3));
        list.add(new Solution(2));

        list.sort(new CustomizedComparator<Solution>(comparatorTwo));
        list.stream().forEach(System.out::println);
        System.out.println("------------");
        list.sort(new CustomizedComparator<Solution>(comparatorOne));
        list.stream().forEach(System.out::println);
    }

    public static class CustomizedComparator<T> implements Comparator<T> {
        private Comparator<T>[] comparators;

        public CustomizedComparator(Comparator... comps) {
            this.comparators = comps;
        }

        @Override
        public int compare(T o1, T o2) {
            int result = 0;
            for (Comparator comparator : comparators) {
                result = comparator.compare(o1, o2);
                if (result != 0) break;
            }
            return result;
        }
//        @Override
//        public int compare(T o1, T o2) {
//            return Arrays.stream(comparators)
//                    .flatMap(comparator -> Stream.of(comparator.compare(o1,o2)))
//                    .filter( num -> num != 0)
//                    .findFirst()
//                    .orElse(0);
//        }
    }
}
