package com.edutie.edutiebackend.domain.entities.studentProfiles;

import com.edutie.edutiebackend.domain.entities.base.EntityBase;
import com.edutie.edutiebackend.domain.entities.studentProfiles.interfaces.IStudentProfile;
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

