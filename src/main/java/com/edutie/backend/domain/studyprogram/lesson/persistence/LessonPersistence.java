package com.edutie.backend.domain.studyprogram.lesson.persistence;

import com.edutie.backend.domain.common.persistence.Persistence;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import validation.Result;
import validation.WrapperResult;

import java.util.List;

public interface LessonPersistence extends Persistence<Lesson, LessonId> {
    /**
     * Retrieve all lessons associated with given course
     *
     * @param courseId course id
     * @return Wrapper result of lesson list
     */
    WrapperResult<List<Lesson>> getAllOfCourseId(CourseId courseId);

    /**
     * Retrieve all lessons associated with given creator
     *
     * @param educatorId educator id
     * @return Wrapper result of lesson list
     */
    WrapperResult<List<Lesson>> getAllOfEducatorId(EducatorId educatorId);

    /**
     * Removes the lesson together with the underlying segments
     *
     * @param lesson lesson to be removed
     * @return Result object
     */
    Result deepRemove(Lesson lesson);
}
