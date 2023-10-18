package com.edutie.edutiebackend.domain.entities.learningProfiles;

import com.edutie.edutiebackend.domain.entities.learningProfiles.interfaces.ILearningProfile;
import com.edutie.edutiebackend.domain.enums.Intelligence;

import java.util.HashMap;

public class IntelligenceProfile implements ILearningProfile {
    private final HashMap<Intelligence, Double> intelligencePoints = new HashMap<>();

    @Override
    public void adjust(byte learningResult) {
        // perform adjusting
    }
}
