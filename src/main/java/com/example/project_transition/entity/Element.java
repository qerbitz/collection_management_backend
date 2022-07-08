package com.example.project_transition.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Element {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany(mappedBy = "collections")
    private List<Tags> tagsList;


    //@ManyToOne(fetch = FetchType.LAZY)
   // @JoinColumn(name = "collections")
   // private Collections collections;
}
