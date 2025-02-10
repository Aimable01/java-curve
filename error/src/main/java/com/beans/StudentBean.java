package com.beans;

public class StudentBean {
    private int studentId;
    private String firstName;
    private String LastName;

    public StudentBean() {}

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    @Override
    public String toString() {
        return "StudentBean{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", LastName='" + LastName + '\'' +
                '}';
    }
}
