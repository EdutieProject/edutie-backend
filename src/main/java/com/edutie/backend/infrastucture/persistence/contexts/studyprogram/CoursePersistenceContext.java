package com.edutie.backend.infrastucture.persistence.contexts.studyprogram;

import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import com.edutie.backend.infrastucture.persistence.contexts.base.PersistenceContext;

import java.util.List;

public interface CoursePersistenceContext extends PersistenceContext<Course, CourseId> {
    /**
     * Retrieve all courses associated with given science
     * @param scienceId science id
     * @return Course list
     */
    List<Course> getAllOfScienceId(ScienceId scienceId);

    /**
     * Retrieve all accessible courses associated with given science
     * @param scienceId science id
     * @return Course list
     */
    List<Course> getAllAccessibleOfScienceId(ScienceId scienceId);
    /**
     * Retrieve all inaccessible courses associated with given science
     * @param scienceId science id
     * @return Course list
     */
    List<Course> getAllInaccessibleOfScienceId(ScienceId scienceId);
}
