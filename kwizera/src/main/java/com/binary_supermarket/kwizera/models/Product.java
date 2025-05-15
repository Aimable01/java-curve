package com.binary_supermarket.kwizera.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String code;
    private String product_type;
    private Double price;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime in_date;

    private String image;
}
