package com.project.PFEBackEnd.controllers;

import com.project.PFEBackEnd.entities.Training;
import com.project.PFEBackEnd.entities.Utilisateur;
import com.project.PFEBackEnd.services.ITrainingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/training")
public class TrainingController {
    /**
     * Constructor Injection is more recommended than Autowiring
     */
    private final ITrainingService trainingService;

    public TrainingController(ITrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @GetMapping(path= "/getAll")
    public List<Training> getAll(){
        return trainingService.getAll();
    }
    @GetMapping(path= "/getTraining/{id}")
    public ResponseEntity<Training> getTrainingById(@PathVariable Long id){
        Training training= trainingService.findById(id);
        return (training== null)
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(training, HttpStatus.OK);
    }
    @PostMapping(path = "/addTraining")
    public Training addTraining(@RequestBody Training training){
        return trainingService.createTraining(training);
    }
    @PutMapping(path = "/updateTraining")
    public Training updateUTraining(@RequestBody Training training){
        return trainingService.updateTraining(training);
    }

    @DeleteMapping(path = "/deleteTraining/{id}")
    public void deleteTraining(@PathVariable Long id){
        trainingService.deleteTraining(id);
    }

}
