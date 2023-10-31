package com.edutie.edutiebackend.domain.student.studentProfiles;

import com.edutie.edutiebackend.domain.common.EntityBase;
import com.edutie.edutiebackend.domain.student.interfaces.IStudentProfile;
import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;

record LearningResultPlaceholder(){}

@Entity
public class LearningHistoryProfile extends EntityBase<LearningHistoryProfile> implements IStudentProfile {

    List<LearningResultPlaceholder> learningHistory = new ArrayList<>();

    @Override
    public void adjust(byte learningResult) {

    }
}

