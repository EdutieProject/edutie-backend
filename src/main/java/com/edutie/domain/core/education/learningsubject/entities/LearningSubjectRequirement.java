package com.edutie.domain.core.education.learningsubject.entities;

import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.elementalrequirement.ElementalRequirement;
import com.edutie.domain.core.education.elementalrequirement.identitites.ElementalRequirementId;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
public class LearningSubjectRequirement extends ElementalRequirement<LearningSubject> {
    private Integer ordinal;


    public static LearningSubjectRequirement create(LearningSubject learningSubject, String title, PromptFragment studentObjective, int orderIndex) {
        LearningSubjectRequirement elementalRequirement = new LearningSubjectRequirement();
        elementalRequirement.setId(new ElementalRequirementId());
        elementalRequirement.title = title;
        elementalRequirement.knowledgeProvider = learningSubject;
        elementalRequirement.studentObjective = studentObjective;
        elementalRequirement.ordinal = orderIndex;
        return elementalRequirement;
    }
}
