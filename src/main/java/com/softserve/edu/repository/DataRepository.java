package com.softserve.edu.repository;

import com.softserve.edu.dto.AverageScore;
import com.softserve.edu.dto.SprintScore;
import com.softserve.edu.entity.Entity;
import com.softserve.edu.entity.Solution;

import java.util.List;

public interface DataRepository {

    void addNewEntity(String name, List<Entity> listForAdding);

    Entity findEntityByName(String name, List<Entity> entityList);

    Entity findEntityById(int id, List<Entity> entityList);

    AverageScore createAverageScoreByStudent(Entity student, List<Solution> listOfSolutions);

    List<SprintScore> createSprintScoresByStudent(Entity student, List<Entity> sprints, List<Solution> listOfSolutions);

    Entity createAndCheckForDuplicate(String name, List<Entity> listForSearch);

    List<String> convertEntityToNameString(List<Entity> entities);

    void validateName(String name);

}
