package com.edutie.edutiebackend.domain.core.learningresult.entities;

import com.edutie.edutiebackend.domain.core.common.base.EntityBase;
import com.edutie.edutiebackend.domain.core.learningresult.entities.base.Assessment;
import com.edutie.edutiebackend.domain.core.lessonsegment.entities.LearningRequirement;
import com.edutie.edutiebackend.domain.core.lessonsegment.identities.LearningRequirementId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
public class LearningAssessment extends Assessment<LearningRequirement> {
}
