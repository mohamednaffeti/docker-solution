package com.project.PFEBackEnd.repositories;

import com.project.PFEBackEnd.entities.Evaluation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface EvaluationRepository extends JpaRepository<Evaluation,Long> {
    Evaluation findByUtilisateurIdAndFormationId(Long idUser, Long idTraining);
    List<Evaluation> findByUtilisateurId(Long idUser);
    List<Evaluation> findByFormationId(Long idTraining);

}
