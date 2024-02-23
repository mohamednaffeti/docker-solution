package com.project.PFEBackEnd.services;

import com.project.PFEBackEnd.entities.Evaluation;

import java.util.List;

public interface IEvaluationService {

    Evaluation getEvaluation(Long idUser , Long idTraining);
    List<Evaluation> getAllByUser(Long idUser);
    List<Evaluation> getAllByTraining(Long idTraining);
    Evaluation addEvaluation (Long idUser , Long idTraining, Evaluation evaluation);
    Evaluation updateEvaluation(Long idUser , Long idTraining, Evaluation evaluation);
    void deleteEvaluation(Long id);
}
