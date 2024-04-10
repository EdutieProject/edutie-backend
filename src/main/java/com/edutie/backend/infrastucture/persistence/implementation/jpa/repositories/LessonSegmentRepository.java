package com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories;

import com.edutie.backend.domain.studyprogram.lessonsegment.Segment;
import com.edutie.backend.domain.studyprogram.lessonsegment.identities.LessonSegmentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonSegmentRepository extends JpaRepository<Segment, LessonSegmentId> {
}

