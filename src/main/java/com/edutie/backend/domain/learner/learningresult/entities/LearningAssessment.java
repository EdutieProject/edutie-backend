package com.edutie.backend.domain.learner.learningresult.entities;

import com.edutie.backend.domain.learner.learningresult.entities.base.Assessment;
import com.edutie.backend.domain.studyprogram.learningrequirement.LearningRequirement;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class LearningAssessment extends Assessment<LearningRequirement> {
}
