package com.edutie.backend.infrastucture.persistence.jpa.repositories;

import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import com.edutie.backend.domain.studyprogram.science.Science;
import org.springframework.data.jpa.repository.*;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, CourseId> {
	List<Course> findCoursesByScience(Science science);

	List<Course> findCoursesByScienceAndAccessible(Science science, boolean accessible);

	List<Course> findCoursesByAuthorEducator(Educator educator);
}
