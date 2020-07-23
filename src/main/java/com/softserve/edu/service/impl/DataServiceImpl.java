package com.softserve.edu.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import com.softserve.edu.entity.Communication;
import com.softserve.edu.entity.Entity;
import com.softserve.edu.entity.Solution;
import com.softserve.edu.repository.DataRepository;
import com.softserve.edu.service.DataService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("dataServiceImpl")
public class DataServiceImpl implements DataService {

    private final List<Entity> students = new LinkedList<>();
    private final List<Entity> mentors = new LinkedList<>();
    private final List<Entity> sprints = new LinkedList<>();
    private final List<Communication> communication = new LinkedList<>();
    private final List<Solution> solution = new LinkedList<>();

    @Qualifier("repositoryImpl")
    private final DataRepository repository;

    public DataServiceImpl(DataRepository repository) {
        this.repository = repository;
    }

    public void addStudent(String studentName) {
        repository.addNewEntity(studentName, students);
    }

    public void addMentor(String mentorName) {
        repository.addNewEntity(mentorName, mentors);
    }

    public void addSprint(String sprintName) {
        repository.addNewEntity(sprintName, sprints);
    }

    public void addCommunication(String studentName, String mentorName) {

        repository.validateName(studentName);
        repository.validateName(mentorName);

        Entity studentEntity = repository.findEntityByName(studentName, students);
        Entity mentorEntity = repository.findEntityByName(mentorName, mentors);

        Communication newCommunication = new Communication(studentEntity.getId(), mentorEntity.getId());
        communication.add(newCommunication);

    }

    public void addSolution(String studentName, String sprintName, int score) {

        repository.validateName(studentName);
        repository.validateName(sprintName);

        Entity studentEntity = repository.findEntityByName(studentName, students);
        Entity sprintEntity = repository.findEntityByName(sprintName, sprints);

        Solution newSolution = new Solution(studentEntity.getId(), sprintEntity.getId(), score);
        solution.add(newSolution);
    }

    public List<Entity> getStudents() {
        return students;
    }

    public List<Entity> getMentors() {
        return mentors;
    }

    public List<Entity> getSprints() {
        return sprints;
    }

    public List<Communication> getCommunication() {
        return communication;
    }

    public List<Solution> getSolution() {
        return solution;
    }

}
