package com.edutie.edutiebackend.infrastucture.persistence.interfaces;

import com.edutie.edutiebackend.domain.core.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {
}
