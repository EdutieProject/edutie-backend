package com.edutie.backend.infrastucture.persistence.implementation.contexts.studyprogram;

import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import org.springframework.stereotype.Component;
import validation.Result;

import java.util.List;
import java.util.Optional;

@Component
public class DefaultCoursePersistence implements CoursePersistence {

    @Override
    public Optional<Course> getById(CourseId courseId) {

        return Optional.empty();
    }

    @Override
    public Result save(Course entity) {
        return null;
    }

    @Override
    public Result deleteById(CourseId courseId) {
        return null;
    }

    @Override
    public List<Course> getAllOfScienceId(ScienceId scienceId) {
        return null;
    }

    @Override
    public List<Course> getAllAccessibleOfScienceId(ScienceId scienceId) {
        return null;
    }

    @Override
    public List<Course> getAllInaccessibleOfScienceId(ScienceId scienceId) {
        return null;
    }
}
