package com.softserve.edu.repository;

import com.softserve.edu.dto.AverageScore;
import com.softserve.edu.dto.SprintScore;
import com.softserve.edu.entity.Entity;
import com.softserve.edu.entity.Solution;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Service("repositoryImpl")
public class DataRepositoryImpl implements DataRepository {

    @Override
    public void addNewEntity(String name, List<Entity> listForAdding) {
        validateName(name);
        Entity newEntity = createAndCheckForDuplicate(name, listForAdding);
        listForAdding.add(newEntity);
    }

    @Override
    public Entity findEntityByName(String entityName, List<Entity> entityList) {
        return entityList
                .stream()
                .filter(e -> e.getName().equals(entityName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Entity with name: %s doesn't exist", entityName)));
    }

    @Override
    public Entity findEntityById(int id, List<Entity> entityList) {
        return entityList.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Entity with id: %d doesn't exist", id)));
    }


    /**
     * Count student's average score and create new {@link AverageScore}
     * with found {@code average} and {@code student} name
     *
     * @param student entity for counting
     * @param listOfSolutions list of {@link Solution}s for counting average score
     * @return new {@link AverageScore}
     */
    @Override
    public AverageScore createAverageScoreByStudent(Entity student, List<Solution> listOfSolutions) {

        double average = listOfSolutions.stream()
                .filter(solution -> solution.getIdStudent() == student.getId())
                .mapToInt(Solution::getScore)
                .average()
                .orElse(0);

        return new AverageScore(student.getName(), average);
    }


    /**
     * Create {@link List< SprintScore >} by given student {@link Entity}
     *
     * Find equals id between {@code student} and {@code listOfSolutions} and
     * by founded id get exist {@link Entity}s in {@code sprints}
     *
     * @param student entity for search
     * @param sprints list of all sprints
     * @param listOfSolutions list of {@link Solution}s to find equality
     * @return  list of {@link SprintScore} for given {@code student}
     */
    public List<SprintScore> createSprintScoresByStudent(Entity student,
                                                        List<Entity> sprints,
                                                        List<Solution> listOfSolutions) {
        return listOfSolutions.stream()
                .filter(solution -> solution.getIdStudent() == student.getId())
                .map(solution -> {
                    Entity sprint = findEntityById(solution.getIdSprint(), sprints);
                    return new SprintScore(sprint.getName(), solution.getScore());
                })
                .collect(toList());

    }


    /**
     * Create new {@link Entity} and check it for containing in {@code listForCheck}
     * And return this new {@link Entity} else throw exception
     *
     * @param name string for check
     * @param listForSearch  list where search
     * @return new {@link Entity} with parametr {@code name}
     * @throws IllegalArgumentException if {@code entityForChecking} exist in {@code listForCheck}
     */
    public Entity createAndCheckForDuplicate(String name, List<Entity> listForSearch) {
        Entity newEntity = new Entity(name);
        if (listForSearch.contains(newEntity))
            throw new IllegalArgumentException(
                    String.format("Entity with name: %s already exist", newEntity.getName()));
        return newEntity;
    }

    /**
     * Convert {@code List<Entity>} to {@code List<String>}
     * where value is entity's name
     *
     * @param entities which must be converted
     * @return list of name from {@code entities}
     */
    public  List<String> convertEntityToNameString(List<Entity> entities) {
        return entities.stream()
                .map(Entity::getName)
                .collect(toList());
    }

    /**
     * Check if {@code name} is null, empty or blank.
     *
     * @param name string for check
     * @throws NullPointerException if {@code name} is null
     * @throws IllegalArgumentException if {@code name} is empty or blank
     */
    public void validateName(String name)  {

        Objects.requireNonNull(name);

        if (name.isEmpty()) throw new IllegalArgumentException("Name is empty");
        if (name.isBlank()) throw new IllegalArgumentException("Name is blank");
    }

}
