package com.edutie.edutiebackend.domain.entities.learningProfiles;

import com.edutie.edutiebackend.domain.entities.learningProfiles.interfaces.ILearningProfile;

import java.util.ArrayList;
import java.util.List;

record LearningActivityPlaceholder(){}

public class LearningProfile implements ILearningProfile {
    List<LearningActivityPlaceholder> learningHistory = new ArrayList<>();

    @Override
    public void adjust(byte learningResult) {

    }
}

