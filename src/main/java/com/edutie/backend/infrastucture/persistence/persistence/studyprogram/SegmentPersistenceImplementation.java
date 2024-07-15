package com.edutie.backend.infrastucture.persistence.persistence.studyprogram;

import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.domain.studyprogram.segment.identities.SegmentId;
import com.edutie.backend.domain.studyprogram.segment.persistence.SegmentPersistence;
import com.edutie.backend.infrastucture.persistence.jpa.repositories.EducatorRepository;
import com.edutie.backend.infrastucture.persistence.jpa.repositories.LessonRepository;
import com.edutie.backend.infrastucture.persistence.jpa.repositories.SegmentRepository;
import com.edutie.backend.infrastucture.persistence.persistence.common.PersistenceError;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
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
     * Override this to provide repository for default methods
     *
     * @return crud jpa repository
     */
    @Override
    public JpaRepository<Segment, SegmentId> getRepository() {
        return segmentRepository;
    }

    /**
     * Override this to provide entity class for default methods
     *
     * @return class of persistence entity
     */
    @Override
    public Class<Segment> entityClass() {
        return Segment.class;
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
//            for (Segment seg : entity.getNextElements()) {
//                seg.setPreviousElement(entity);
//                segmentRepository.save(seg);
//            } //TODO: #98
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
                    .map(educator -> WrapperResult.successWrapper(segmentRepository.findSegmentsByAuthorEducator(educator)))
                    .orElseGet(() -> WrapperResult.failureWrapper(PersistenceError.notFound(Lesson.class)));
        } catch (Exception exception) {
            return WrapperResult.failureWrapper(PersistenceError.exceptionEncountered(exception));
        }
    }
}
