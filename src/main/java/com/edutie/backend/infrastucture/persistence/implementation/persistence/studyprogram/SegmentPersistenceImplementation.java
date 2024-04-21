package com.edutie.backend.infrastucture.persistence.implementation.persistence.studyprogram;

import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.domain.studyprogram.segment.identities.SegmentId;
import com.edutie.backend.domain.studyprogram.segment.persistence.SegmentPersistence;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.EducatorRepository;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.LessonRepository;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.SegmentRepository;
import com.edutie.backend.infrastucture.persistence.implementation.persistence.common.PersistenceError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Result;
import validation.WrapperResult;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SegmentPersistenceImplementation implements SegmentPersistence {
    private final SegmentRepository segmentRepository;
    private final LessonRepository lessonRepository;
    private final EducatorRepository educatorRepository;

    /**
     * Retrieves the entity using its identifier. Entity is wrapped in optional, therefore
     * it might not be present based on the identifier presence in the database.
     *
     * @param segmentId entity id
     * @return Optional entity
     */
    @Override
    public WrapperResult<Segment> getById(SegmentId segmentId) {
        try {
            Optional<Segment> segmentOptional = segmentRepository.findById(segmentId);
            return segmentOptional.map(WrapperResult::successWrapper)
                    .orElseGet(() -> WrapperResult.failureWrapper(PersistenceError.notFound(Segment.class)));
        } catch (Exception exception) {
            return WrapperResult.failureWrapper(PersistenceError.exceptionEncountered(exception));
        }
    }

    /**
     * Persists the provided entity into the database. If it is already present,
     * updates its state to the provided object's state. Returns result indicating whether
     * the operation was successful or not
     *
     * @param entity entity
     * @return Result object
     */
    @Override
    public Result save(Segment entity) {
        try {
            segmentRepository.saveAndFlush(entity);
            return Result.success();
        } catch (Exception exception) {
            return Result.failure(PersistenceError.exceptionEncountered(exception));
        }
    }

    /**
     * Removes the entity of the provided id from the database. If the entity is not preset or could not be
     * deleted, does nothing and returns failure result.
     *
     * @param segmentId entity id
     * @return Result object
     */
    @Override
    public Result removeById(SegmentId segmentId) {
        try {
            segmentRepository.deleteById(segmentId);
            return Result.success();
        } catch (Exception exception) {
            return Result.failure(PersistenceError.exceptionEncountered(exception));
        }
    }

    /**
     * Retrieve all lesson segments associated with given lesson
     *
     * @param lessonId lesson id
     * @return Lesson Segment list
     */
    @Override
    public WrapperResult<List<Segment>> getAllOfLessonId(LessonId lessonId) {
        try {
            Optional<Lesson> optionalLesson = lessonRepository.findById(lessonId);
            return optionalLesson
                    .map(lesson -> WrapperResult.successWrapper(segmentRepository.findSegmentsByLesson(lesson)))
                    .orElseGet(() -> WrapperResult.failureWrapper(PersistenceError.notFound(Lesson.class)));
        } catch (Exception exception) {
            return WrapperResult.failureWrapper(PersistenceError.exceptionEncountered(exception));
        }
    }

    /**
     * Retrieve all lesson segments associated with given creator
     *
     * @param educatorId educator id
     * @return Lesson Segment list
     */
    @Override
    public WrapperResult<List<Segment>> getAllOfEducatorId(EducatorId educatorId) {
        try {
            Optional<Educator> optionalEducator = educatorRepository.findById(educatorId);
            return optionalEducator
                    .map(educator -> WrapperResult.successWrapper(segmentRepository.findSegmentsByEducator(educator)))
                    .orElseGet(() -> WrapperResult.failureWrapper(PersistenceError.notFound(Lesson.class)));
        } catch (Exception exception) {
            return WrapperResult.failureWrapper(PersistenceError.exceptionEncountered(exception));
        }
    }
}
