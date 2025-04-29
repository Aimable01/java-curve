package com.aimable.beginer.models.day2;

import jakarta.persistence.*;

@Entity
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lapId;
    private String lapDesc;

    public int getLapId() {
        return lapId;
    }

    public void setLapId(int lapId) {
        this.lapId = lapId;
    }

    public String getLapDesc() {
        return lapDesc;
    }

    public void setLapDesc(String lapDesc) {
        this.lapDesc = lapDesc;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "lapId=" + lapId +
                ", lapDesc='" + lapDesc + '\'' +
                '}';
    }
}
