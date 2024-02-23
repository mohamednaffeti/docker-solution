package com.project.PFEBackEnd.services;

import com.project.PFEBackEnd.entities.Training;
import com.project.PFEBackEnd.repositories.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingServiceImpl implements ITrainingService {

   private final TrainingRepository trainingRepository;

    public TrainingServiceImpl(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Override
    public List<Training> getAll() {
        return trainingRepository.findAll();
    }

    @Override
    public Training createTraining(Training training) {
        return trainingRepository.save(training);
    }

    @Override
    public Training updateTraining(Training training) {
        Training training1 = findById(training.getId());
        if (training1 != null){
            trainingRepository.save(training);
            return training;
        }else{
            throw new RuntimeException("Training does not exist");
        }

    }

    @Override
    public void deleteTraining(Long id) {
        trainingRepository.deleteById(id);
    }

    @Override
    public Training findById(Long id) {
        return trainingRepository.findById(id).orElse(null);
    }
}
