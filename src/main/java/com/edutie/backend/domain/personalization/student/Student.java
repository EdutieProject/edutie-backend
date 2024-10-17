package com.edutie.backend.domain.personalization.student;

import com.edutie.backend.domain.administration.Role;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.entities.ElementalRequirement;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.student.identities.StudentId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Student class conceals all the student characteristics of the user.
 * Note that the relationship with the user is maintained by createdBy field in the base class.
 */
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Student extends Role<StudentId> {
    @OneToMany(targetEntity = LearningResult.class, fetch = FetchType.LAZY, mappedBy = "student")
    private final List<LearningResult> learningHistory = new ArrayList<>();

    public static Student create(UserId userId) {
        Student student = new Student();
        student.setOwnerUserId(userId);
        student.setId(new StudentId());
        return student;
    }

    public List<LearningResult> getLearningHistoryByKnowledgeSubject(LearningResultPersistence persistence, KnowledgeSubjectId knowledgeSubjectId) {
        return persistence.getLearningResultsOfStudentByKnowledgeSubjectId(this.getId(), knowledgeSubjectId).getValue();
    }
}
