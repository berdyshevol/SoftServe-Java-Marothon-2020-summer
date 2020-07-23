package com.softserve.edu.service.impl;

import com.softserve.edu.entity.Entity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@SpringBootTest
public class ApplicationTest {

    @Autowired
    protected DataServiceImpl dataService;

    @BeforeEach
    public void setUpData() {

        dataService.addStudent("student1");             //  id = 1
        dataService.addStudent("student2");             //  id = 2
        dataService.addStudent("student3");             //  id = 3
        dataService.addStudent("student4");             //  id = 4
        dataService.addStudent("student5");             //  id = 5
        dataService.addStudent("student6");             //  id = 6

        dataService.addMentor("mentor1");               //  id = 7
        dataService.addMentor("mentor2");               //  id = 8

        dataService.addSprint("sprint1");                //  id = 9
        dataService.addSprint("sprint2");                //  id = 10
        dataService.addSprint("sprint3");                //  id = 11

        dataService.addCommunication("student1", "mentor1");
        dataService.addCommunication("student2", "mentor1");
        dataService.addCommunication("student3", "mentor1");
        dataService.addCommunication("student4", "mentor1");

        dataService.addSolution("student1", "sprint1", 0);
        dataService.addSolution("student1", "sprint2", 300);
        dataService.addSolution("student1", "sprint3", 0);

        dataService.addSolution("student2", "sprint1", 200);
        dataService.addSolution("student2", "sprint2", 200);
        dataService.addSolution("student2", "sprint3", 200);

        dataService.addSolution("student3", "sprint1", 300);
        dataService.addSolution("student3", "sprint2", 200);
        dataService.addSolution("student3", "sprint3", 400);

    }

    @AfterEach
    public void tearDown() {

        dataService.getStudents().clear();
        dataService.getMentors().clear();
        dataService.getSprints().clear();
        dataService.getCommunication().clear();
        dataService.getSolution().clear();

        try {
            Field field = Entity.class.getDeclaredField("counter");
            field.setAccessible(true);
            Constructor<?> constructor = Entity.class.getDeclaredConstructor(String.class);
            Object obj = constructor.newInstance("test");
            field.set(obj, 1);
        } catch (NoSuchFieldException | NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
            System.out.println("exception in AfterEach");
            e.printStackTrace();
        }

    }

}
