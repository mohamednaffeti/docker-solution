package com.project.PFEBackEnd.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Training implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String titre;
    String description;
    @ManyToMany
    @JoinTable(
            name = "evaluation",
            joinColumns = @JoinColumn(name = "formation_id"),
            inverseJoinColumns = @JoinColumn(name = "utilisateur_id")
    )
    private List<Utilisateur> utilisateurs;
    @OneToMany(mappedBy = "formation")
    private List<Evaluation> evaluations;
}
