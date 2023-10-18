package com.edutie.edutiebackend.domain.entities.learningProfiles.interfaces;

public interface ILearningProfile {
    /**
     * Method which adjusts learning profile's parameters based on learning activity
     */
    void adjust(byte learningResult);
}
