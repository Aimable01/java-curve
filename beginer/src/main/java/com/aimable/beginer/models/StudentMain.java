package com.aimable.beginer.models;

import com.aimable.beginer.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class StudentMain {
    public static void main(String[] args) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        // Create a new student
        Student student = new Student();

        // Set the embedded StudentNames object
        StudentNames names = new StudentNames();
        names.setFirstName("John");
        names.setMiddleName("Mike");
        names.setLastName("Doe");
        student.setName(names);

        // Set the age (transient field, will not be persisted)5
        student.setAge(22);

        // Save the student to the database
        session.persist(student);
        transaction.commit();
        session.close();

        System.out.println("Student saved successfully!");
    }
}