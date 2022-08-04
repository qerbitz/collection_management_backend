package com.example.project_transition.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    private String role;
    private String[] authorities;
    private boolean isActive;
    private boolean isNotLocked;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
}
