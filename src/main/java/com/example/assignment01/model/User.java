package com.example.assignment01.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fullname;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String address;

    private String password;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date create;

    @Temporal(TemporalType.DATE)
    @Column(name = "updated_at")
    private Date update;

    @Column(name = "is_active")
    private boolean enabled;

    private String username;

    @Column(name = "is_admin")
    private boolean roles;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Order> orders = new ArrayList<>();


}
