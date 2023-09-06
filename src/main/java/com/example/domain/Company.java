package com.example.domain;

import javax.persistence.*;

@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String nation;

    @Column(nullable = false)
    private String region;
}
