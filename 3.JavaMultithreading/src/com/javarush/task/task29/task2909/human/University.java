package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {
    private List<Student> students;
    private String name;
    private int age;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public University(String name, int age) {
        this.students = new ArrayList<>();
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        //TODO:
        return students
                .stream()
                .filter(x -> averageGrade == x.getAverageGrade())
                .findFirst()
                .get();
    }

    public Student getStudentWithMaxAverageGrade() {
        //TODO:
        return students
                .stream()
                .max((x, y) -> Double.compare(x.getAverageGrade(), y.getAverageGrade()))
                .get();
    }

    public Student getStudentWithMinAverageGrade() {
        //TODO:
        return students
                .stream()
                .min((x, y) -> Double.compare(x.getAverageGrade(), y.getAverageGrade()))
                .get();
    }

    public void expel(Student student) {
        students.remove(student);
    }
}