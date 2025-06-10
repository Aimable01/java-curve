package com.example.sec.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true, nullable = false)
    private String title;
    private String author;
    private String description;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;
}
