package com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories;

import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import com.edutie.backend.domain.studyprogram.science.Science;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, CourseId> {
	List<Course> findCoursesByScience(Science science);

	List<Course> findCoursesByScienceAndAccessible(Science science, boolean accessible);

	List<Course> findCoursesByEducator(Educator educator);
}
