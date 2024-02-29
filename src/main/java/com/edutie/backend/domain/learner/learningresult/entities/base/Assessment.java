package com.edutie.backend.domain.learner.learningresult.entities.base;

import com.edutie.backend.domain.common.base.EntityBase;
import com.edutie.backend.domain.learner.learningresult.identities.AssessmentId;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Assessment<TAssessedEntity> extends EntityBase<AssessmentId> {
    @Column(name = "assessment_points")
    int assessmentPoints;
    @ManyToOne(fetch = FetchType.EAGER)
    TAssessedEntity entity;
}
