package com.example.project_transition.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Collections {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_category")
    private Category category;

   // @OneToMany(mappedBy = "collections")
  //  private List<Element> elementList;

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false, updatable = false)
    private User user;
}
