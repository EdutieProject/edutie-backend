package com.edutie.domain.core.learning.student;

import com.edutie.domain.core.administration.Role;
import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.common.DomainErrors;
import com.edutie.domain.core.common.base.AuditableEntityBase;
import com.edutie.domain.core.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.domain.core.learning.learningresult.LearningResult;
import com.edutie.domain.core.learning.learningresult.entities.Assessment;
import com.edutie.domain.core.learning.learningresult.persistence.LearningResultPersistence;
import com.edutie.domain.core.learning.student.identities.StudentId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import validation.WrapperResult;

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

    public static Student create(UserId userId) {
        Student student = new Student();
        student.setOwnerUserId(userId);
        student.setId(new StudentId());
        return student;
    }

    private static LocalDateTime getLatestResultsDateThreshold() {
        return LocalDateTime.now().minusDays(7);
    }

}
