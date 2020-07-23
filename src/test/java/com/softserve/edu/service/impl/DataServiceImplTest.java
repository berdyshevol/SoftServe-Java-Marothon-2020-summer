package com.softserve.edu.service.impl;

import com.softserve.edu.entity.Communication;
import com.softserve.edu.entity.Entity;
import com.softserve.edu.entity.Solution;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class DataServiceImplTest extends ApplicationTest {

    @Test
    public void addStudentTest() {

        List<Entity> expected = new ArrayList<>();

        expected.add(new Entity("student1"));
        expected.add(new Entity("student2"));
        expected.add(new Entity("student3"));
        expected.add(new Entity("student4"));
        expected.add(new Entity("student5"));
        expected.add(new Entity("student6"));


        assertArrayEquals(expected.toArray(), dataService.getStudents().toArray());
    }

    @Test
    public void addMentorTest() {

        List<Entity> expected = new ArrayList<>();

        expected.add(new Entity("mentor1"));
        expected.add(new Entity("mentor2"));

        assertArrayEquals(expected.toArray(), dataService.getMentors().toArray());
    }

    @Test
    public void addSprintTest() {

        List<Entity> expected = new ArrayList<>();

        expected.add(new Entity("sprint1"));
        expected.add(new Entity("sprint2"));
        expected.add(new Entity("sprint3"));

        assertArrayEquals(expected.toArray(), dataService.getSprints().toArray());
    }

    @Test
    public void addNullCheckTest() {
        assertThrows(NullPointerException.class, () -> dataService.addStudent(null));
    }

    @Test
    public void addBlankCheckTest() {
        assertThrows(IllegalArgumentException.class, () -> dataService.addMentor("      "));
    }

    @Test
    public void addEmptyCheckTest() {
        assertThrows(IllegalArgumentException.class, () -> dataService.addSprint(""));
    }

    @Test
    public void addCommunicationTest() {

        List<Communication> expected = new ArrayList<>();

        expected.add(new Communication(1, 7));
        expected.add(new Communication(2, 7));
        expected.add(new Communication(3, 7));
        expected.add(new Communication(4, 7));


        assertArrayEquals(expected.toArray(), dataService.getCommunication().toArray());
    }

    @Test
    public void addSolutionTest() {

        List<Solution> expected = new ArrayList<>();

        expected.add(new Solution(1, 9, 0));
        expected.add(new Solution(1, 10, 300));
        expected.add(new Solution(1, 11, 0));

        expected.add(new Solution(2, 9, 200));
        expected.add(new Solution(2, 10, 200));
        expected.add(new Solution(2, 11, 200));

        expected.add(new Solution(3, 9, 300));
        expected.add(new Solution(3, 10, 200));
        expected.add(new Solution(3, 11, 400));

        assertArrayEquals(expected.toArray(), dataService.getSolution().toArray());
    }

    @Test
    public void addDuplicationNameTest() {
        dataService.getStudents().add(new Entity("student1"));
        assertThrows(IllegalArgumentException.class, () -> dataService.addStudent("student1"));
    }

    @Test
    public void findEntityByNameTest() {
        assertThrows(IllegalArgumentException.class, () -> dataService.addCommunication("notExistName1", "notExistName2"));
    }

}