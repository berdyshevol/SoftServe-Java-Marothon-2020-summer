package com.softserve.edu.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.softserve.edu.dto.SprintScore;
import com.softserve.edu.entity.Entity;
import com.softserve.edu.entity.Solution;
import com.softserve.edu.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softserve.edu.dto.AverageScore;
import com.softserve.edu.dto.MentorStudent;
import com.softserve.edu.dto.StudentScore;
import com.softserve.edu.service.DataService;
import com.softserve.edu.service.MarathonService;

import static java.util.stream.Collectors.*;

@Service("marathonServiceImpl")
public class MarathonServiceImpl implements MarathonService {

    @Qualifier("dataServiceImpl")
    private final DataService dataService;

    @Qualifier("repositoryImpl")
    private final DataRepository repository;

    @Autowired
    public MarathonServiceImpl(DataService dataService, DataRepository repository) {
        this.dataService = dataService;
        this.repository = repository;
    }

    public List<String> getStudents() {
        List<Entity> students = dataService.getStudents();
        return repository.convertEntityToNameString(students);
    }

    public List<String> getMentors() {
        List<Entity> mentors = dataService.getMentors();
        return repository.convertEntityToNameString(mentors);
    }

    public StudentScore studentResult(String studentName) {

        repository.validateName(studentName);

        Entity student = repository.findEntityByName(studentName, dataService.getStudents());
        List<Solution> solutions = dataService.getSolution();

        List<SprintScore> sprintScoresByStudent =
                repository.createSprintScoresByStudent(student, dataService.getSprints(), dataService.getSolution());

        return new StudentScore(student.getName(), sprintScoresByStudent);
    }

    public List<StudentScore> allStudentsResult() {
        return dataService.getStudents().stream()
                .map(student -> studentResult(student.getName()))
                .collect(toList());
    }

    public AverageScore studentAverage(String studentName) {
        repository.validateName(studentName);
        Entity student = repository.findEntityByName(studentName, dataService.getStudents());
        return repository.createAverageScoreByStudent(student, dataService.getSolution());
    }

    public List<AverageScore> allStudentsAverage() {
        return dataService.getStudents().stream()
                .map(student -> studentAverage(student.getName()))
                .collect(toList());
    }

    public MentorStudent mentorStudents(String mentorName) {
        int mentorId = repository.findEntityByName(mentorName, dataService.getMentors()).getId();
        List<String> studentNames = dataService
                .getCommunication()
                .stream()
                .filter(c -> c.getIdMentor() == mentorId)
                .map(c -> repository.findEntityById(c.getIdStudent(), dataService.getStudents()).getName())
                .collect(Collectors.toList());
        return new MentorStudent(mentorName, studentNames);
    }
}
