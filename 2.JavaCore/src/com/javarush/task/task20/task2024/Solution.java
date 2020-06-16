package com.javarush.task.task20.task2024;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/* 
Знакомство с графами
*/
public class Solution implements Serializable {
    int node;
    List<Solution> edges = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        Solution solution = new Solution();
        solution.node = 5;
        solution.edges.add(solution);
        solution.edges.add(new Solution());
        solution.edges.add(new Solution());
        System.out.println(solution.edges);
        oos.writeObject(solution);

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        Solution solution1 = (Solution) ois.readObject();
        System.out.println(solution1.edges);
        for (Solution sol : solution1.edges) {
            System.out.println(sol.node);
        }
    }
}
