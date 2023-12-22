package com.edutie.edutiebackend.application.services.management.course;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.edutie.edutiebackend.application.services.common.servicebase.GenericRetrievalService;
import com.edutie.edutiebackend.domain.core.course.Course;
import com.edutie.edutiebackend.domain.core.course.identities.CourseId;

public interface CourseService extends GenericRetrievalService<Course, CourseId> {
    /**
     * Implementation should retrieve courses that are assigned to the corresponding science
     * @param ScienceId science id
     * @return Courses HashSet as Set
     */
    Set<Course> getByScienceId(UUID ScienceId);
    /**
     * @param courseID
     * @param newScienceId
     * @return
     */
    boolean changeCourseScienceId(UUID courseID, UUID newScienceId);
    /**
     * @param newAccessibility
     * @param courseId
     * @return
     */
    boolean changeAccessibility(boolean newAccessibility, UUID courseId); // may be improved with Access Levels in the future
    /**
     * @param courseId
     * @return
     */
    boolean removeCourseById(UUID courseId);
    /**
     * @param newCourseName
     * @param newCourseDescription
     * @return
     */
    Optional<Course> createEmptyCourse(String newCourseName, String newCourseDescription);
}
