package com.edutie.edutiebackend.domain.core.student.entites;

import com.edutie.edutiebackend.domain.core.common.studenttraits.Ability;
import com.edutie.edutiebackend.domain.core.student.entites.base.LearningParameter;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class AbilityLearningParameter extends LearningParameter<Ability> {
}
