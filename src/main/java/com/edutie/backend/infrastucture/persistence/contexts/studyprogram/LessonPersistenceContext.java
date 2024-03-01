package com.edutie.backend.infrastucture.persistence.contexts.studyprogram;

import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import com.edutie.backend.infrastucture.persistence.contexts.base.PersistenceContext;

import java.util.List;

public interface LessonPersistenceContext extends PersistenceContext<Lesson, LessonId> {
    /**
     * Retrieve all lessons associated with given course
     * @param courseId course id
     * @return Lesson list
     */
    List<Lesson> getAllOfCourseId(CourseId courseId);

    /**
     * Retrieve all lessons associated with given creator
     * @param educatorId creator id
     * @return Lesson list
     */
    List<Lesson> getAllOfCreatorId(EducatorId educatorId);
}
