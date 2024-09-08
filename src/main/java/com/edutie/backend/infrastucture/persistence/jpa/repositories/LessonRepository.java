package com.edutie.backend.infrastucture.persistence.jpa.repositories;

import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import org.springframework.data.jpa.repository.*;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, LessonId> {
	List<Lesson> getLessonsByCourse(Course course);

	List<Lesson> getLessonsByAuthorEducator(Educator educator);
}
