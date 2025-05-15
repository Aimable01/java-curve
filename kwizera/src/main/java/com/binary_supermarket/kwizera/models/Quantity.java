package com.binary_supermarket.kwizera.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Data
public class Quantity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_code", referencedColumnName = "code")
    private Product product;

    private Integer quantity;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime date;
}