package com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories;

import com.edutie.backend.domain.core.lessonsegment.LessonSegment;
import com.edutie.backend.domain.core.lessonsegment.identities.LessonSegmentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonSegmentRepository extends JpaRepository<LessonSegment, LessonSegmentId> {
}
