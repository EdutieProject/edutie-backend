package com.edutie.backend.infrastucture.persistence.jpa.repositories;

import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.identities.LearningResultId;
import com.edutie.backend.domain.personalization.student.Student;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface LearningResultRepository extends JpaRepository<LearningResult, LearningResultId> {
    List<LearningResult> findLearningResultsByStudentOrderByCreatedOnDesc(Student student, Limit limit);

    List<LearningResult> findLearningResultsByStudentAndCreatedOnGreaterThanOrderByCreatedOnDesc(Student student, LocalDateTime localDate, Limit limit);

    List<LearningResult> findLearningResultsBySolutionSubmissionLearningResourceDefinitionIdAndStudent(LearningResourceDefinitionId solutionSubmission_learningResource_definitionId, Student student);

    @Query("SELECT lr FROM LearningResult lr " +
            "JOIN lr.solutionSubmission ss " +
            "JOIN ss.learningResource lrsc " +
            "JOIN lrsc.qualifiedRequirements elReqs " +
            "JOIN elReqs.learningRequirement lreq " +
            "WHERE lreq = :learningRequirement " +
            "AND lr.student = :student"
    )
    List<LearningResult> findLearningResultsByLearningRequirement(@Param("student") Student student, @Param("learningRequirement") LearningRequirement learningRequirement);
}
