package com.aimable.beginer.models.day1;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("car")
public class Car extends Vehicle {
    private int numberOfDoors;

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }
}
