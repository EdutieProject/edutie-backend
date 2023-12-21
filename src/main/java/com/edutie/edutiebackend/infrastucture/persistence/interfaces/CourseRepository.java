package com.edutie.edutiebackend.infrastucture.persistence.interfaces;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutie.edutiebackend.domain.core.course.Course;

public interface CourseRepository extends JpaRepository<Course, UUID> {
}
