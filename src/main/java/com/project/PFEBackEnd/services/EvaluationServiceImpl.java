package com.project.PFEBackEnd.services;

import com.project.PFEBackEnd.entities.Evaluation;
import com.project.PFEBackEnd.entities.Training;
import com.project.PFEBackEnd.entities.Utilisateur;
import com.project.PFEBackEnd.repositories.EvaluationRepository;
import com.project.PFEBackEnd.repositories.TrainingRepository;
import com.project.PFEBackEnd.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationServiceImpl implements IEvaluationService {
    private final EvaluationRepository evaluationRepository;
    private final UserRepository userRepository;
    private final TrainingRepository trainingRepository;

    public EvaluationServiceImpl(EvaluationRepository evaluationRepository, UserRepository userRepository, TrainingRepository trainingRepository) {
        this.evaluationRepository = evaluationRepository;
        this.userRepository = userRepository;
        this.trainingRepository = trainingRepository;
    }

    @Override
    public Evaluation getEvaluation(Long idUser, Long idTraining) {
        return evaluationRepository.findByUtilisateurIdAndFormationId(idUser, idTraining);
    }

    @Override
    public List<Evaluation> getAllByUser(Long idUser) {
        return evaluationRepository.findByUtilisateurId(idUser);
    }

    @Override
    public List<Evaluation> getAllByTraining(Long idTraining) {
        return evaluationRepository.findByFormationId(idTraining);
    }

    @Override
    public Evaluation addEvaluation(Long idUser, Long idTraining, Evaluation evaluation) {
        evaluation.setUtilisateur(userRepository.findById(idUser).orElse(null));
        evaluation.setFormation(trainingRepository.findById(idTraining).orElse(null));
        if(evaluation.getUtilisateur()==null || evaluation.getFormation()==null){
            throw new RuntimeException("User or Training does not exist");
        }else{
            return evaluationRepository.save(evaluation);
        }

    }

    @Override
    public Evaluation updateEvaluation(Long idUser, Long idTraining, Evaluation evaluation) {
        Evaluation existingEvaluation = evaluationRepository.findById(evaluation.getId())
                .orElseThrow(() -> new RuntimeException("Evaluation does not exist"));

        Utilisateur user = userRepository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("User does not exist"));
        Training training = trainingRepository.findById(idTraining)
                .orElseThrow(() -> new RuntimeException("Training does not exist"));

        existingEvaluation.setNote(evaluation.getNote());
        existingEvaluation.setCommentaire(evaluation.getCommentaire());
        existingEvaluation.setUtilisateur(user);
        existingEvaluation.setFormation(training);

        return evaluationRepository.save(evaluation);

    }

    @Override
    public void deleteEvaluation(Long id) {
        evaluationRepository.deleteById(id);

    }
}
