package com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories;

import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.domain.studyprogram.segment.identities.SegmentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonSegmentRepository extends JpaRepository<Segment, SegmentId> {
}

