package com.edutie.backend.domain.studyprogram.lesson.persistence;

import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import com.edutie.backend.domain.common.persistence.Persistence;
import validation.Result;
import validation.WrapperResult;

import java.util.List;

public interface LessonPersistence extends Persistence<Lesson, LessonId> {
    /**
     * Retrieve all lessons associated with given course
     * @param courseId course id
     * @return Lesson list
     */
    WrapperResult<List<Lesson>> getAllOfCourseId(CourseId courseId);

    /**
     * Retrieve all lessons associated with given creator
     * @param educatorId educator id
     * @return Lesson list
     */
    WrapperResult<List<Lesson>> getAllOfEducatorId(EducatorId educatorId);

    /**
     * Deep save the lesson together with all the underlying segments.
     * @param lesson lesson to save
     * @return result object
     */
    Result deepSave(Lesson lesson);
}
