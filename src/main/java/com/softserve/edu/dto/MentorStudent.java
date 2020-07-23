package com.softserve.edu.dto;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MentorStudent {
    private String mentortName;
    private List<String> studentNames;

    public MentorStudent(String mentortName, List<String> studentNames) {
        this.mentortName = mentortName;
        this.studentNames = studentNames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MentorStudent)) return false;

        MentorStudent that = (MentorStudent) o;

        if (mentortName != null ? !mentortName.equals(that.mentortName) : that.mentortName != null) return false;
        return studentNames != null ? studentNames.equals(that.studentNames) : that.studentNames == null;
    }

    @Override
    public int hashCode() {
        int result = mentortName != null ? mentortName.hashCode() : 0;
        result = 31 * result + (studentNames != null ? studentNames.hashCode() : 0);
        return result;
    }
}
