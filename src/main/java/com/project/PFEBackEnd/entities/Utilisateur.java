package com.project.PFEBackEnd.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String userName;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    String password;
    Date creationDate;
    boolean isActive;
    private String role;
    @JsonIgnore
    @ManyToMany(mappedBy = "utilisateurs")
    private List<Training> formations;
}
