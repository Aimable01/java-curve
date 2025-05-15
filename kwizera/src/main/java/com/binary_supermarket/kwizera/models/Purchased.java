package com.binary_supermarket.kwizera.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Data
public class Purchased {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_code", referencedColumnName = "code")
    private Product product;

    private int quantity;
    private Double total;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime date;
}