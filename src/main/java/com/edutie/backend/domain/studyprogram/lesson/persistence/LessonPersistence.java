package com.edutie.backend.domain.studyprogram.lesson.persistence;

import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import com.edutie.backend.domain.common.persistence.PersistenceBase;

import java.util.List;

public interface LessonPersistence extends PersistenceBase<Lesson, LessonId> {
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
