package com.aimable.beginer.models.day0;

import jakarta.persistence.*;

@Entity // Marks this class as an entity
@Table(name = "students") // Specifies the table name
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for auto-increment
    private int studId;

    @Embedded // Indicates that this is an embedded object
    private StudentNames name;

    @Transient // This field will not be persisted in the database
    private int age;

    // Default constructor
    public Student() {
    }

    // Getters and Setters
    public int getStudId() {
        return studId;
    }

    public void setStudId(int studId) {
        this.studId = studId;
    }

    public StudentNames getName() {
        return name;
    }

    public void setName(StudentNames name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studId=" + studId +
                ", name=" + name +
                ", age=" + age +
                '}';
    }
}