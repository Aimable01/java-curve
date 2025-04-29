package com.aimable.beginer.models.day1;

import com.aimable.beginer.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class VehicleMain {
    public static void main(String[] args) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Car c = new Car();
        c.setManufacturer("Toyota");
        c.setNumberOfDoors(4);

        Bike b = new Bike();
        b.setManufacturer("Toyota");
        b.setHasCarrier(true);


        // Save to the database
        session.persist(c);
        session.persist(b);
        transaction.commit();
        session.close();

        System.out.println("Student saved successfully!");
    }
}
