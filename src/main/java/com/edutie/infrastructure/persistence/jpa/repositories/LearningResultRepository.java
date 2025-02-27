package com.edutie.infrastructure.persistence.jpa.repositories;

import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.domain.core.learning.learningresult.LearningResult;
import com.edutie.domain.core.learning.learningresult.identities.LearningResultId;
import com.edutie.domain.core.learning.student.Student;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface LearningResultRepository extends JpaRepository<LearningResult, LearningResultId> {
    List<LearningResult> findLearningResultsByStudentOrderByCreatedOnDesc(Student student, Limit limit);

    List<LearningResult> findLearningResultsByStudentAndCreatedOnAfterOrderByCreatedOnDesc(Student student, LocalDateTime localDate, Limit limit);

    @Query("SELECT lr FROM LearningResult lr " +
            "JOIN lr.solutionSubmission ss " +
            "JOIN LearningExperience lrsc ON lrsc.id = ss.learningResourceId " +
            "JOIN lrsc.requirements elReqs " +
            "JOIN elReqs.learningRequirement lreq " +
            "WHERE lreq = :learningRequirement " +
            "AND lr.student = :student"
    )
    List<LearningResult> findStudentsLearningResultsByLearningRequirement(@Param("student") Student student, @Param("learningRequirement") LearningSubject learningSubject);

    @Query("SELECT lr FROM LearningResult lr " +
            "JOIN lr.solutionSubmission ss " +
            "JOIN LearningExperience lrsc ON lrsc.id = ss.learningResourceId " +
            "WHERE lrsc.definitionId IN :learningResourceDefinitionIds " +
            "AND lr.student = :student"
    )
    List<LearningResult> findLearningResultsByDefinitionIdsAndStudent(@Param("student") Student student, @Param("learningResourceDefinitionIds") Set<LearningResourceDefinitionId> learningResourceDefinitionIds);
}
