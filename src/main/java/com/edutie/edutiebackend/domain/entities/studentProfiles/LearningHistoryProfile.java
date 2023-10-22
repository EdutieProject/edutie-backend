package com.edutie.edutiebackend.domain.entities.studentProfiles;

import com.edutie.edutiebackend.domain.entities.studentProfiles.interfaces.IStudentProfile;

import java.util.ArrayList;
import java.util.List;

record LearningResultPlaceholder(){}

public class LearningHistoryProfile implements IStudentProfile {
    List<LearningResultPlaceholder> learningHistory = new ArrayList<>();

    @Override
    public void adjust(byte learningResult) {

    }
}

