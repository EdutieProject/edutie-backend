package com.edutie.edutiebackend.application.viewmodels;

import com.edutie.edutiebackend.domain.core.learningresult.LearningResult;
import lombok.Data;

/**
 * Class wrapping around learning result and the difference it made on the student's
 * learning params.
 */
//TODO: incorporate into domain?
@Data
public class LearningEffect {
    LearningResult learningResult;
    Double difference;
}
