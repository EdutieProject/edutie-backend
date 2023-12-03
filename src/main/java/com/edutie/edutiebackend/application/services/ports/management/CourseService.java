package com.edutie.edutiebackend.application.services.ports.management;

import com.edutie.edutiebackend.application.services.ports.common.GenericRetrievalService;
import com.edutie.edutiebackend.domain.core.course.Course;
import com.edutie.edutiebackend.domain.core.course.identities.CourseId;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface CourseService extends GenericRetrievalService<Course, CourseId> {
    /**
     * Implementation should retrieve courses that are assigned to the corresponding science
     * @param ScienceId science id
     * @return Courses HashSet as Set
     */
    Set<Course> getByScienceId(UUID ScienceId);
    boolean changeCurseScienceId(UUID courseID, UUID newScienceId);
    boolean changeAccessibility(boolean newAccessibility, UUID courseId); // may be improved with Access Levels in the future
    boolean removeCourseById(UUID courseId);
    Optional<Course> createEmptyCourse(String newCourseName, String newCourseDescription);
}
