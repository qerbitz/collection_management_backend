package com.example.project_transition.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String iconImage;
    private Date createDate;
    private int visits;
    private String technicalState;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private List<Comment> comments;

    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private List<Image> images;

    @OneToOne(fetch = FetchType.EAGER)
    private Category category;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;
}
