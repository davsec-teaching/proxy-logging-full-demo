package com.ecs160.model;

import com.ecs160.annotations.Log;

public class Student {
    private String name;
    private int age;
    private String studentId;

    public Student() {
    }
    public Student(String name, int age, String studentId) {
        this.name = name;
        this.age = age;
        this.studentId = studentId;
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

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Log
    public boolean isEligibleToVote() {
        return age >= 18;
    }

    @Log
    public boolean hasValidStudentId() {
        return studentId != null && !studentId.trim().isEmpty();
    }
}
