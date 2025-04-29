package com.aimable.beginer.models.day2;

import com.aimable.beginer.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class StudentMain {

    public static void main(String[] args) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Laptop laptop = new Laptop();
        laptop.setLapDesc("TOSHIB");

        Students student = new Students();
        student.setFirstName("celia");
        student.setLastName("furaha");
        student.setLaptop(laptop);


        // Save to the database
        session.save(laptop);
        session.save(student);
        transaction.commit();
        session.close();

        System.out.println("Student saved successfully!");
    }

}
