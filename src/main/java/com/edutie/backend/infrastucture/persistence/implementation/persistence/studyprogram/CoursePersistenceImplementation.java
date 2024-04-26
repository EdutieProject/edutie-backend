package com.edutie.backend.infrastucture.persistence.implementation.persistence.studyprogram;

import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import com.edutie.backend.domain.studyprogram.course.persistence.CoursePersistence;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.*;
import com.edutie.backend.infrastucture.persistence.implementation.persistence.common.PersistenceError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import validation.Result;
import validation.WrapperResult;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CoursePersistenceImplementation implements CoursePersistence {
    private final CourseRepository courseRepository;
    private final ScienceRepository scienceRepository;
    private final LessonRepository lessonRepository;
    private final SegmentRepository segmentRepository;
    private final EducatorRepository educatorRepository;
    /**
     * Retrieves the entity using its identifier. Entity is wrapped in optional, therefore
     * it might not be present based on the identifier presence in the database.
     *
     * @param courseId entity id
     * @return Optional entity
     */
    @Override
    public WrapperResult<Course> getById(CourseId courseId) {
        try {
            Optional<Course> optionalCourse = courseRepository.findById(courseId);
            return optionalCourse
                    .map(Result::successWrapper)
                    .orElseGet(() -> Result.failureWrapper(PersistenceError.notFound(Educator.class)));
        } catch (Exception exception) {
            return Result.failureWrapper(PersistenceError.exceptionEncountered(exception));
        }
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
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Result save(Course entity) {
        try {
            courseRepository.save(entity);
            return Result.success();
        } catch (Exception exception) {
            return Result.failure(PersistenceError.exceptionEncountered(exception));
        }
    }

    /**
     * Removes the entity of the provided id from the database. If the entity is not preset or could not be
     * deleted, does nothing and returns failure result.
     *
     * @param courseId entity id
     * @return Result object
     */
    @Override
    public Result removeById(CourseId courseId) {
        try {
            courseRepository.deleteById(courseId);
            return Result.success();
        } catch (Exception exception) {
            return Result.failure(PersistenceError.exceptionEncountered(exception));
        }
    }

    /**
     * Saves the course together with all its contents. This function
     * goes through the whole provided course and updates states of the course
     * together with all the underlying entities.
     *
     * @param course course to save
     * @return Result object
     */
    @Override
    public Result deepSave(Course course) {
        try {
            for (Lesson lesson : course.getLessons()) {
                for (Segment segment : lesson.getSegments()) {
                    segmentRepository.saveAndFlush(segment);
                }
                lessonRepository.saveAndFlush(lesson);
            }
            courseRepository.saveAndFlush(course);
            return Result.success();
        } catch (Exception exception) {
            return Result.failure(PersistenceError.exceptionEncountered(exception));
        }
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
            List<Course> courses = courseRepository.findCoursesByEducator(educatorOptional.get());
            return WrapperResult.successWrapper(courses);
        } catch (Exception exception) {
            return Result.failureWrapper(PersistenceError.exceptionEncountered(exception));
        }
    }
}
