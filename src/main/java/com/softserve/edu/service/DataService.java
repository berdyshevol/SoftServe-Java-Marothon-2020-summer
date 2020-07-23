package com.softserve.edu.service;

import com.softserve.edu.entity.Communication;
import com.softserve.edu.entity.Entity;
import com.softserve.edu.entity.Solution;

import java.util.List;

public interface DataService {

    public void addStudent(String studentName);

    public void addMentor(String mentorName);

    public void addSprint(String sprintName);

    public void addCommunication(String studentName, String mentorName);
    
    public void addSolution(String studentName, String sprintName, int score);

    List<Entity> getStudents();
  
    List<Communication> getCommunication();

    List<Entity> getMentors();

    List<Entity> getSprints();

    List<Solution> getSolution();
    
}
