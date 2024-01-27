package com.edutie.edutiebackend.infrastucture.persistence.implementation.jpa.repositories;

import com.edutie.edutiebackend.domain.core.lessonsegment.LessonSegment;
import com.edutie.edutiebackend.domain.core.lessonsegment.identities.LessonSegmentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonSegmentRepository extends JpaRepository<LessonSegment, LessonSegmentId> {
}
