package com.edutie.backend.infrastructure.persistence.jpa.repositories;

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
import java.util.Set;

public interface LearningResultRepository extends JpaRepository<LearningResult, LearningResultId> {
    List<LearningResult> findLearningResultsByStudentOrderByCreatedOnDesc(Student student, Limit limit);

    List<LearningResult> findLearningResultsByStudentAndCreatedOnAfterOrderByCreatedOnDesc(Student student, LocalDateTime localDate, Limit limit);

    @Query("SELECT lr FROM LearningResult lr " +
            "JOIN lr.solutionSubmission ss " +
            "JOIN LearningResource lrsc ON lrsc.id = ss.learningResourceId " +
            "JOIN lrsc.qualifiedRequirements elReqs " +
            "JOIN elReqs.learningRequirement lreq " +
            "WHERE lreq = :learningRequirement " +
            "AND lr.student = :student"
    )
    List<LearningResult> findStudentsLearningResultsByLearningRequirement(@Param("student") Student student, @Param("learningRequirement") LearningRequirement learningRequirement);

    @Query("SELECT lr FROM LearningResult lr " +
            "JOIN lr.solutionSubmission ss " +
            "JOIN LearningResource lrsc ON lrsc.id = ss.learningResourceId " +
            "WHERE lrsc.definitionId IN :learningResourceDefinitionIds " +
            "AND lr.student = :student"
    )
    List<LearningResult> findLearningResultsByDefinitionIdsAndStudent(@Param("student") Student student, @Param("learningResourceDefinitionIds") Set<LearningResourceDefinitionId> learningResourceDefinitionIds);
}
