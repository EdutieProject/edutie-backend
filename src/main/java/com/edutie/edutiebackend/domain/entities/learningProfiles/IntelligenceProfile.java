package com.edutie.edutiebackend.domain.entities.learningProfiles;

import com.edutie.edutiebackend.domain.entities.learningProfiles.interfaces.ILearningProfile;
import com.edutie.edutiebackend.domain.enums.Intelligence;

import java.util.HashMap;

public class IntelligenceProfile implements ILearningProfile {
    private final HashMap<Intelligence, Double> intelligencePoints = new HashMap<>();

    /**
     * Default constructor. Should be changed in the future to utilize database values.
     */
    public IntelligenceProfile() {
        for (Intelligence intelligence :
                Intelligence.values()) {
            intelligencePoints.put(intelligence, 0.0);
        }
    }

    @Override
    public void adjust(byte learningResult) {
        // perform adjusting
    }
}
