package com.edutie.backend.domain.studyprogram.course.persistence;

import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import com.edutie.backend.domain.common.persistence.PersistenceBase;
import validation.Result;

import java.util.List;

public interface CoursePersistence extends PersistenceBase<Course, CourseId> {
    /**
     * Saves the course together with all its contents. This function
     * goes through the whole provided course and updates states of the course
     * together with all the underlying entities.
     * @param course course to save
     * @return Result object
     */
    Result deepSave(Course course);
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
    /**
     * Retrieve all courses created by given educator
     * @param educatorId educator id
     * @return Course list
     */
    List<Course> getAllOfEducatorId(EducatorId educatorId);
}
