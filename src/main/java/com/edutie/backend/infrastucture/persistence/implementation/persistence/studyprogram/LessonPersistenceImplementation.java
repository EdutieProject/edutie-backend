package com.edutie.backend.infrastucture.persistence.implementation.persistence.studyprogram;

import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.CourseRepository;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.EducatorRepository;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.LessonRepository;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.SegmentRepository;
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
public class LessonPersistenceImplementation implements LessonPersistence {
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;
    private final EducatorRepository educatorRepository;
    private final SegmentRepository segmentRepository;
    /**
     * Retrieves the entity using its identifier. Entity is wrapped in optional, therefore
     * it might not be present based on the identifier presence in the database.
     *
     * @param lessonId entity id
     * @return Optional entity
     */
    @Override
    public WrapperResult<Lesson> getById(LessonId lessonId) {
        try {
            Optional<Lesson> lessonOptional = lessonRepository.findById(lessonId);
            return lessonOptional
                    .map(WrapperResult::successWrapper)
                    .orElseGet(() -> WrapperResult.failureWrapper(PersistenceError.notFound(Lesson.class)));
        } catch (Exception exception) {
            return WrapperResult.failureWrapper(PersistenceError.exceptionEncountered(exception));
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
    public Result save(Lesson entity) {
        try {
            lessonRepository.saveAndFlush(entity);
            return Result.success();
        } catch (Exception exception) {
            return Result.failure(PersistenceError.exceptionEncountered(exception));
        }
    }

    /**
     * Removes the entity of the provided id from the database. If the entity is not preset or could not be
     * deleted, does nothing and returns failure result.
     *
     * @param lessonId entity id
     * @return Result object
     */
    @Override
    public Result removeById(LessonId lessonId) {
        try {
            lessonRepository.deleteById(lessonId);
            return Result.success();
        } catch (Exception exception) {
            return Result.failure(PersistenceError.exceptionEncountered(exception));
        }
    }

    /**
     * Retrieve all lessons associated with given course
     *
     * @param courseId course id
     * @return Lesson list
     */
    @Override
    public WrapperResult<List<Lesson>> getAllOfCourseId(CourseId courseId) {
        try {
            Optional<Course> course = courseRepository.findById(courseId);
            if (course.isEmpty())
                return Result.failureWrapper(PersistenceError.notFound(Course.class));
            List<Lesson> lessons = lessonRepository.getLessonsByCourse(course.get());
            return WrapperResult.successWrapper(lessons);
        } catch (Exception exception) {
            return Result.failureWrapper(PersistenceError.exceptionEncountered(exception));
        }
    }

    /**
     * Retrieve all lessons associated with given creator
     *
     * @param educatorId educator id
     * @return Lesson list
     */
    @Override
    public WrapperResult<List<Lesson>> getAllOfEducatorId(EducatorId educatorId) {
        try {
            Optional<Educator> educator = educatorRepository.findById(educatorId);
            if (educator.isEmpty())
                return Result.failureWrapper(PersistenceError.notFound(Course.class));
            List<Lesson> lessons = lessonRepository.getLessonsByEducator(educator.get());
            return WrapperResult.successWrapper(lessons);
        } catch (Exception exception) {
            return Result.failureWrapper(PersistenceError.exceptionEncountered(exception));
        }
    }
}
