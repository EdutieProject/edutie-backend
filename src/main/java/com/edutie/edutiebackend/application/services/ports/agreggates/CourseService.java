package com.edutie.edutiebackend.application.services.ports.agreggates;

import com.edutie.edutiebackend.application.services.ports.crud.GenericRetrievalService;
import com.edutie.edutiebackend.domain.core.course.Course;
import com.edutie.edutiebackend.domain.core.course.identities.CourseId;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface CourseService extends GenericRetrievalService<Course, CourseId> {
    Set<Course> getByScienceId(UUID ScienceId);
    boolean changeScienceId(UUID newScienceId);
    boolean changeAccessibility(boolean newAccessibility, UUID courseId); // may be improved with Access Levels in the future
    boolean removeCourseById(UUID courseId);
    Optional<Course> createEmptyCourse(String newCourseName, String newCourseDescription);
}
