package com.edutie.backend.infrastucture.persistence.implementation.studyprogram;

import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import com.edutie.backend.infrastucture.persistence.jpa.repositories.CourseRepository;
import com.edutie.backend.infrastucture.persistence.jpa.repositories.EducatorRepository;
import com.edutie.backend.infrastucture.persistence.jpa.repositories.ScienceRepository;
import com.edutie.backend.infrastucture.persistence.PersistenceError;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import validation.Result;
import validation.WrapperResult;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CoursePersistenceImplementation implements CoursePersistence {
    private final CourseRepository courseRepository;
    private final ScienceRepository scienceRepository;
    private final EducatorRepository educatorRepository;

    /**
     * Override this to provide repository for default methods
     *
     * @return crud jpa repository
     */
    @Override
    public JpaRepository<Course, CourseId> getRepository() {
        return courseRepository;
    }

    /**
     * Override this to provide entity class for default methods
     *
     * @return class of persistence entity
     */
    @Override
    public Class<Course> entityClass() {
        return Course.class;
    }

    /**
     * Retrieve all courses associated with given science
     *
     * @param scienceId science id
     * @return Wrapper result of the desired list
     */
    @Override
    public WrapperResult<List<Course>> getAllOfScienceId(ScienceId scienceId) {
        try {
            Optional<Science> scienceOptional = scienceRepository.findById(scienceId);
            if (scienceOptional.isEmpty())
                return Result.failureWrapper(PersistenceError.notFound(Science.class));
            List<Course> courses = courseRepository.findCoursesByScience(scienceOptional.get());
            return WrapperResult.successWrapper(courses);
        } catch (Exception exception) {
            return Result.failureWrapper(PersistenceError.exceptionEncountered(exception));
        }
    }

    /**
     * Retrieve all accessible courses associated with given science
     *
     * @param scienceId science id
     * @return Wrapper result of the desired list
     */
    @Override
    public WrapperResult<List<Course>> getAllAccessibleOfScienceId(ScienceId scienceId) {
        try {
            Optional<Science> scienceOptional = scienceRepository.findById(scienceId);
            if (scienceOptional.isEmpty())
                return Result.failureWrapper(PersistenceError.notFound(Science.class));
            List<Course> courses = courseRepository.findCoursesByScienceAndAccessible(scienceOptional.get(), true);
            return WrapperResult.successWrapper(courses);
        } catch (Exception exception) {
            return Result.failureWrapper(PersistenceError.exceptionEncountered(exception));
        }
    }

    /**
     * Retrieve all courses created by given educator
     *
     * @param educatorId educator id
     * @return Wrapper result of the desired list
     */
    @Override
    public WrapperResult<List<Course>> getAllOfEducatorId(EducatorId educatorId) {
        try {
            Optional<Educator> educatorOptional = educatorRepository.findById(educatorId);
            if (educatorOptional.isEmpty())
                return Result.failureWrapper(PersistenceError.notFound(Educator.class));
            List<Course> courses = courseRepository.findCoursesByAuthorEducator(educatorOptional.get());
            return WrapperResult.successWrapper(courses);
        } catch (Exception exception) {
            return Result.failureWrapper(PersistenceError.exceptionEncountered(exception));
        }
    }
}
