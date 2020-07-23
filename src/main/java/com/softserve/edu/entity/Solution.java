package com.softserve.edu.entity;

import java.util.Objects;

public class Solution {

    private int idStudent;
    private int idSprint;
    private int score;


    public Solution(int idStudent, int idSprint, int score) {
        this.idStudent = idStudent;
        this.idSprint = idSprint;
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solution solution = (Solution) o;
        return idStudent == solution.idStudent &&
                idSprint == solution.idSprint &&
                score == solution.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStudent, idSprint, score);
    }

    @Override
    public String toString() {
        return "Solution{" +
                "idStudent=" + idStudent +
                ", idSprint=" + idSprint +
                ", score=" + score +
                '}';
    }

    public int getIdStudent() {
        return idStudent;
    }

    public int getIdSprint() {
        return idSprint;
    }

    public int getScore() {
        return score;
    }
}
