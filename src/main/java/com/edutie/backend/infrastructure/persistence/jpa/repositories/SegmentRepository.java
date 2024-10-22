package com.edutie.backend.infrastructure.persistence.jpa.repositories;

import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.domain.studyprogram.segment.identities.SegmentId;
import org.springframework.data.jpa.repository.*;

import java.util.List;

public interface SegmentRepository extends JpaRepository<Segment, SegmentId> {
	List<Segment> findSegmentsByLesson(Lesson lesson);

	List<Segment> findSegmentsByAuthorEducator(Educator educator);
}
