package com.edutie.domain.core.learning.learningexperience.implementations;

import com.edutie.domain.core.education.elementalrequirement.ElementalRequirement;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.entities.LearningExperienceRequirement;
import com.edutie.domain.core.learning.learningexperience.entities.activity.SimpleProblemActivity;
import com.edutie.domain.core.learning.learningexperience.entities.notes.LearningNotes;
import com.edutie.domain.core.learning.learningexperience.identities.LearningExperienceId;
import com.edutie.domain.core.learning.student.Student;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;

import java.util.Set;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
public class SimpleProblemActivityLearningExperience extends LearningExperience<SimpleProblemActivity> {

    public static SimpleProblemActivityLearningExperience create(
            Student student,
            SimpleProblemActivity activity,
            LearningNotes learningNotes,
            Set<ElementalRequirement> elementalRequirements
    ) {
        SimpleProblemActivityLearningExperience learningExperience = new SimpleProblemActivityLearningExperience();
        learningExperience.setId(new LearningExperienceId());
        learningExperience.setCreatedBy(student.getOwnerUserId());
        learningExperience.setStudentId(student.getId());
        learningExperience.setActivity(activity);
        learningExperience.setNotes(learningNotes);
        learningExperience.setRequirements(elementalRequirements.stream().map(LearningExperienceRequirement::from).collect(Collectors.toSet()));
        return learningExperience;
    }
}
