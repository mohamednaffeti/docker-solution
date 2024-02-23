package com.project.PFEBackEnd.services;

import com.project.PFEBackEnd.entities.Training;
import com.project.PFEBackEnd.entities.Utilisateur;

import java.util.List;

public interface ITrainingService {

    List<Training> getAll();
    Training createTraining(Training training);
    Training updateTraining(Training training);
    void deleteTraining(Long id);
    Training findById(Long id);
}
