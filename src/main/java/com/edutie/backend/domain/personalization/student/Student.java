package com.edutie.backend.domain.personalization.student;

import com.edutie.backend.domain.administration.Role;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.student.identities.StudentId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Student class conceals all the student characteristics of the user.
 * Note that the relationship with the user is maintained by createdBy field in the base class.
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Setter
@Getter
@Entity
public class Student extends Role<StudentId> {
    @OneToMany(targetEntity = LearningResult.class, fetch = FetchType.EAGER) //TODO: lazy load
    private final List<LearningResult> learningHistory = new ArrayList<>();

    public static Student create(UserId userId) {
        Student student = new Student();
        student.setOwnerUserId(userId);
        student.setId(new StudentId());
        return student;
    }

    public List<LearningResult> getLearningHistoryByLearningRequirement(LearningRequirementId learningRequirementId) {
        return learningHistory.stream()
                .filter(o -> o.getSolutionSubmission().getLearningResourceDefinition().getLearningRequirements()
                        .stream().anyMatch(x -> x.getId().equals(learningRequirementId)))
                .collect(Collectors.toList());
    }
}
