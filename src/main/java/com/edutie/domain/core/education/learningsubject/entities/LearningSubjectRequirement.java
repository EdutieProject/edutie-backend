package com.edutie.domain.core.education.learningsubject.entities;

import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.elementalrequirement.ElementalRequirement;
import com.edutie.domain.core.education.elementalrequirement.identitites.ElementalRequirementId;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
public class LearningSubjectRequirement extends ElementalRequirement {
    private Integer ordinal;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "learning_subject_id")
    @JsonIgnore
    private LearningSubject learningSubject;


    public static LearningSubjectRequirement create(LearningSubject learningSubject, String title, PromptFragment studentObjective, int orderIndex) {
        LearningSubjectRequirement elementalRequirement = new LearningSubjectRequirement();
        elementalRequirement.setId(new ElementalRequirementId());
        elementalRequirement.title = title;
        elementalRequirement.learningSubject = learningSubject;
        elementalRequirement.studentObjective = studentObjective;
        elementalRequirement.ordinal = orderIndex;
        return elementalRequirement;
    }
}
