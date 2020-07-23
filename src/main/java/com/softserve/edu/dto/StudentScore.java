package com.softserve.edu.dto;

import java.util.List;
import java.util.Objects;

public class StudentScore {

    private String studentName;
    private List<SprintScore> sprintScore;

    public StudentScore(String studentName, List<SprintScore> sprintScore) {
        this.studentName = studentName;
        this.sprintScore = sprintScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentScore that = (StudentScore) o;
        return Objects.equals(studentName, that.studentName) &&
                Objects.equals(sprintScore, that.sprintScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentName, sprintScore);
    }

    public String getStudentName() {
        return studentName;
    }

    public List<SprintScore> getSprintScore() {
        return sprintScore;
    }

    @Override
    public String toString() {
        return "StudentScore{" +
                "studentName='" + studentName + '\'' +
                ", sprintScore=" + sprintScore +
                '}';
    }
}
