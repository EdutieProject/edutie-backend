package com.edutie.backend.infrastucture.persistence.jpa.repositories;

import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.identities.LearningResultId;
import com.edutie.backend.domain.personalization.student.Student;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface LearningResultRepository extends JpaRepository<LearningResult, LearningResultId> {
    List<LearningResult> findLearningResultsByStudentOrderByCreatedOn(Student student, Limit limit);

    List<LearningResult> findLearningResultsByStudentAndCreatedOnGreaterThanOrderByCreatedOn(Student student, LocalDateTime localDate, Limit limit);
}
