package com.softserve.edu.entity;

import java.util.Objects;

public class Communication {

    private int idStudent;
    private int idMentor;

    public Communication(int idStudent, int idMentor) {
        this.idStudent = idStudent;
        this.idMentor = idMentor;
    }

    public int getIdMentor() {
        return idMentor;
    }

    public int getIdStudent() {
        return idStudent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Communication that = (Communication) o;
        return idStudent == that.idStudent &&
                idMentor == that.idMentor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStudent, idMentor);
    }

    @Override
    public String toString() {
        return "Communication{" +
                "idStudent=" + idStudent +
                ", idMentor=" + idMentor +
                '}';
    }
}
