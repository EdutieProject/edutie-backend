package com.edutie.backend.application.viewmodels;

import com.edutie.backend.domain.core.learningresult.LearningResult;

import lombok.Data;

/**
 * Class wrapping around learning result and the difference it made on the student's
 * learning params.
 */
@Data
public class LearningEffect {
    LearningResult learningResult;
    double difference;
}
