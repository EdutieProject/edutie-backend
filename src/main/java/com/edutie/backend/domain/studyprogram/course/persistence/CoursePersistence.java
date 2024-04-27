package com.edutie.backend.domain.studyprogram.course.persistence;

import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import com.edutie.backend.domain.common.persistence.Persistence;
import validation.Result;
import validation.WrapperResult;

import java.util.List;

public interface CoursePersistence extends Persistence<Course, CourseId> {
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
     * @return Wrapper result of the desired list
     */
    WrapperResult<List<Course>> getAllOfScienceId(ScienceId scienceId);

    /**
     * Retrieve all accessible courses associated with given science
     * @param scienceId science id
     * @return Wrapper result of the desired list
     */
    WrapperResult<List<Course>> getAllAccessibleOfScienceId(ScienceId scienceId);
    /**
     * Retrieve all courses created by given educator
     * @param educatorId educator id
     * @return Wrapper result of the desired list
     */
    WrapperResult<List<Course>> getAllOfEducatorId(EducatorId educatorId);
}
