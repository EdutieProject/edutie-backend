package com.edutie.edutiebackend.domain.core.student.interfaces;

public interface IStudentProfile {
    /**
     * Method which adjusts learning profile's parameters based on learning activity
     */
    void adjust(byte learningResult);
}
