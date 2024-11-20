package com.edutie.backend.domain.personalization.student;

import com.edutie.backend.domain.administration.Role;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.entities.Assessment;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Grade;
import com.edutie.backend.domain.personalization.student.identities.StudentId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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

    private static LocalDateTime getLatestResultsDateThreshold() {
        return LocalDateTime.now().minusDays(7);
    }

    /**
     * Retrieves Learning Results of student associated with learning requirements linked to a given knowledge subject.
     *
     * @param persistence        persistence of learning results
     * @param knowledgeSubjectId knowledge subject id
     * @return list of learning results.
     */
    public List<LearningResult> getLearningHistoryByKnowledgeSubject(LearningResultPersistence persistence, KnowledgeSubjectId knowledgeSubjectId) {
        return persistence.getLearningResultsOfStudentByKnowledgeSubjectId(this.getId(), knowledgeSubjectId).getValue();
    }

    /**
     * Retrieves latest assessments filtering them by maximal grade
     *
     * @param persistence persistence of learning results
     * @param maxGrade    max grade
     * @return list of assessments
     */
    public List<Assessment> getLatestAssessmentsByMaxGrade(LearningResultPersistence persistence, Grade maxGrade) {
        return persistence.getLatestResultsOfStudent(this.getId(), 20, getLatestResultsDateThreshold()).getValue()
                .stream().flatMap(o -> o.getAssessments().stream())
                .filter(o -> o.getGrade().lessThanOrEqual(maxGrade))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves latest learning results in the chronological order. Where the results gathering moment begin is
     * defined by a <code>getLatestResultsDateThreshold</code> function
     *
     * @param persistence persistence of learning results
     * @see #getLatestResultsDateThreshold() date treshold getter
     * @return list of learning results
     */
    public List<LearningResult> getLatestLearningResults(LearningResultPersistence persistence) {
        return persistence.getLatestResultsOfStudent(this.getId(), 20, getLatestResultsDateThreshold()).getValue()
                .stream().sorted(Comparator.comparing(AuditableEntityBase::getCreatedOn)).collect(Collectors.toList());
    }
}
