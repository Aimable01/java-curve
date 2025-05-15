package com.binary_supermarket.kwizera.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    private String name;
    private String productType;
    private Double price;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime inDate;

    private String image;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Quantity> quantities;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<CartItem> cartItems;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Purchase> purchases;
}