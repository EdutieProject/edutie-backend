package com.edutie.edutiebackend.domain.core.learningresult.entities;

import com.edutie.edutiebackend.domain.core.learningresult.entities.base.Assessment;
import com.edutie.edutiebackend.domain.core.learningrequirement.LearningRequirement;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class LearningAssessment extends Assessment<LearningRequirement> {
}
