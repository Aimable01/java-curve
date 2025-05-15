package com.binary_supermarket.kwizera.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Cart cart;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "product_code", referencedColumnName = "code")
    private Product product;

    private int quantity;

    @CreationTimestamp
    private LocalDateTime addedDate;
}
