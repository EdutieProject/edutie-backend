package com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories;

import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.domain.studyprogram.segment.identities.SegmentId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SegmentRepository extends JpaRepository<Segment, SegmentId> {
    List<Segment> findSegmentsByLesson(Lesson lesson);

    List<Segment> findSegmentsByEducator(Educator educator);
}

