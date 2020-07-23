package com.softserve.edu.service.impl;

import com.softserve.edu.dto.AverageScore;
import com.softserve.edu.dto.MentorStudent;
import com.softserve.edu.dto.SprintScore;
import com.softserve.edu.dto.StudentScore;
import com.softserve.edu.entity.Entity;
import com.softserve.edu.service.DataService;
import com.softserve.edu.service.MarathonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MarathonServiceImplTest extends ApplicationTest {

    @Autowired
    private MarathonService marathonService;

    @Test
    void getStudents() {

        List<String> expected = new ArrayList<>();

        expected.add("student1");
        expected.add("student2");
        expected.add("student3");
        expected.add("student4");
        expected.add("student5");
        expected.add("student6");

        List<String> actual = marathonService.getStudents();
        assertEquals(expected, actual);

    }

    @Test
    void getMentors() {

        List<String> expected = new ArrayList<>();

        expected.add("mentor1");
        expected.add("mentor2");

        List<String> actual = marathonService.getMentors();

        assertEquals(expected, actual);

    }

    @Test
    void studentResult() {

        StudentScore expected = new StudentScore("student3",
                List.of(new SprintScore("sprint1", 300),
                        new SprintScore("sprint2", 200),
                        new SprintScore("sprint3", 400)));

        StudentScore actual = marathonService.studentResult("student3");

        assertEquals(expected, actual);

    }

    @Test
    void allStudentsResult() {

        List<StudentScore> expected = new ArrayList<>();

        expected.add(new StudentScore("student1",
                List.of(new SprintScore("sprint1", 0),
                        new SprintScore("sprint2", 300),
                        new SprintScore("sprint3", 0))));

        expected.add(new StudentScore("student2",
                List.of(new SprintScore("sprint1", 200),
                        new SprintScore("sprint2", 200),
                        new SprintScore("sprint3", 200))));

        expected.add(new StudentScore("student3",
                List.of(new SprintScore("sprint1", 300),
                        new SprintScore("sprint2", 200),
                        new SprintScore("sprint3", 400))));

        expected.add(new StudentScore("student4", List.of()));
        expected.add(new StudentScore("student5", List.of()));
        expected.add(new StudentScore("student6", List.of()));

        List<StudentScore> actual = marathonService.allStudentsResult();

        assertArrayEquals(expected.toArray(), actual.toArray());

    }

    @Test
    void studentAverage() {

        AverageScore expected = new AverageScore("student1", 100);
        AverageScore actual = marathonService.studentAverage("student1");
        assertEquals(expected, actual);

    }

    @Test
    void allStudentsAverage() {

        List<AverageScore> expected = new ArrayList<>();
        expected.add(new AverageScore("student1", 100));
        expected.add(new AverageScore("student2", 200));
        expected.add(new AverageScore("student3", 300));
        expected.add(new AverageScore("student4", 0));
        expected.add(new AverageScore("student5", 0));
        expected.add(new AverageScore("student6", 0));

        List<AverageScore> actual = marathonService.allStudentsAverage();

        System.out.println(expected);
        System.out.println(actual);

        assertArrayEquals(expected.toArray(), actual.toArray());

    }

    @Test
    void mentorStudents() {
        MentorStudent expectedMentor1 = new MentorStudent(
                "mentor1",
                List.of("student1", "student2", "student3", "student4")
        );

        MentorStudent actualMentor1 = marathonService.mentorStudents("mentor1");
        assertTrue(expectedMentor1.equals(actualMentor1));



    }
}