package com.aimable.beginer.models;

import com.aimable.beginer.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class StudentMain {
    public static void main(String[] args) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Student student = new Student();
        student.setFirstName("Sylvie");
        student.setLastName("Uwera");
        session.persist(student);
        transaction.commit();
        session.close();
    }
}
