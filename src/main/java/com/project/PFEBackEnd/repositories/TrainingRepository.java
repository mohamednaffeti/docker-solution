package com.project.PFEBackEnd.repositories;

import com.project.PFEBackEnd.entities.Training;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface TrainingRepository extends JpaRepository<Training,Long> {

}
