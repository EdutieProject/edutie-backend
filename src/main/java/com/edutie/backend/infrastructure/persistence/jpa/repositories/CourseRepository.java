package com.edutie.backend.infrastructure.persistence.jpa.repositories;

import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import com.edutie.backend.domain.studyprogram.science.Science;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, CourseId> {
	List<Course> findCoursesByScience(Science science);

	List<Course> findCoursesByScienceAndAccessible(Science science, boolean accessible);

	List<Course> findCoursesByAuthorEducator(Educator educator);

	@Query("SELECT c FROM Course c " +
			"JOIN c.lessons l " +
			"JOIN l.segments s " +
			"WHERE s.learningResourceDefinitionId = :definitionId")
	Optional<Course> findCourseByLearningResourceDefinitionId(@Param("definitionId") LearningResourceDefinitionId definitionId);
}
