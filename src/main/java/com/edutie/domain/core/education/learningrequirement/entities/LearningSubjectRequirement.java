package com.edutie.domain.core.education.learningrequirement.entities;

import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.elementalrequirement.ElementalRequirement;
import com.edutie.domain.core.education.learningrequirement.LearningSubject;
import com.edutie.domain.core.education.elementalrequirement.identitites.ElementalRequirementId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class LearningSubjectRequirement extends ElementalRequirement {
    private String title;
    private Integer ordinal;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "learning_subject_id")
    @JsonIgnore
    private LearningSubject learningSubject;


    public static LearningSubjectRequirement create(LearningSubject learningSubject, String title, PromptFragment studentObjective, int orderIndex) {
        LearningSubjectRequirement elementalRequirement = new LearningSubjectRequirement();
        elementalRequirement.setId(new ElementalRequirementId());
        elementalRequirement.learningSubject = learningSubject;
        elementalRequirement.studentObjective = studentObjective;
        elementalRequirement.ordinal = orderIndex;
        return elementalRequirement;
    }
}
