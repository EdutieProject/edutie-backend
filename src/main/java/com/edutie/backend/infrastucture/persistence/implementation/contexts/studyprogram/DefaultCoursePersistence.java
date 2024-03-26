package com.edutie.backend.infrastucture.persistence.implementation.contexts.studyprogram;

import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import org.springframework.stereotype.Component;
import validation.Error;
import validation.Result;
import validation.WrapperResult;

import java.util.List;
import java.util.Optional;

@Component
public class DefaultCoursePersistence implements CoursePersistence {

    /**
     * Retrieves the entity using its identifier. Entity is wrapped in optional, therefore
     * it might not be present based on the identifier presence in the database.
     *
     * @param courseId entity id
     * @return Optional entity
     */
    @Override
    public WrapperResult<Course> getById(CourseId courseId) {
        return WrapperResult.failureWrapper(new Error("", ""));
    }

    /**
     * Persists the provided entity into the database. If it is already present,
     * updates its state to the provided object's state. Returns result indicating whether
     * the operation was successful or not
     *
     * @param entity
     * @return Result object
     */
    @Override
    public Result save(Course entity) {
        return null;
    }

    /**
     * Removes the entity of the provided id from the database. If the entity is not preset or could not be
     * deleted, does nothing and returns failure result.
     *
     * @param courseId entity id
     * @return Result object
     */
    @Override
    public Result deleteById(CourseId courseId) {
        return null;
    }

    /**
     * Retrieve all courses associated with given science
     *
     * @param scienceId science id
     * @return Course list
     */
    @Override
    public List<Course> getAllOfScienceId(ScienceId scienceId) {
        return null;
    }

    /**
     * Retrieve all accessible courses associated with given science
     *
     * @param scienceId science id
     * @return Course list
     */
    @Override
    public List<Course> getAllAccessibleOfScienceId(ScienceId scienceId) {
        return null;
    }

    /**
     * Retrieve all inaccessible courses associated with given science
     *
     * @param scienceId science id
     * @return Course list
     */
    @Override
    public List<Course> getAllInaccessibleOfScienceId(ScienceId scienceId) {
        return null;
    }

    /**
     * Retrieve all courses created by given educator
     *
     * @param educatorId educator id
     * @return Course list
     */
    @Override
    public List<Course> getAllOfEducatorId(EducatorId educatorId) {
        return null;
    }
}
