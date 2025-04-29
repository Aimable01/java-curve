package com.aimable.beginer.models.day1;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("bike")
public class Bike extends Vehicle {
    private boolean hasCarrier;

    public boolean isHasCarrier() {
        return hasCarrier;
    }

    public void setHasCarrier(boolean hasCarrier) {
        this.hasCarrier = hasCarrier;
    }
}
