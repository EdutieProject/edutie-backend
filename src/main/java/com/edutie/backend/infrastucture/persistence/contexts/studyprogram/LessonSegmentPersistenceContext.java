package com.edutie.backend.infrastucture.persistence.contexts.studyprogram;

import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import com.edutie.backend.domain.studyprogram.lessonsegment.LessonSegment;
import com.edutie.backend.domain.studyprogram.lessonsegment.identities.LessonSegmentId;
import com.edutie.backend.infrastucture.persistence.contexts.base.PersistenceContext;

import java.util.List;

public interface LessonSegmentPersistenceContext extends PersistenceContext<LessonSegment, LessonSegmentId> {
    /**
     * Retrieve all lesson segments associated with given lesson
     * @param lessonId lesson id
     * @return Lesson Segment list
     */
    List<LessonSegment> getAllOfLessonId(LessonId lessonId);

    /**
     * Retrieve all lesson segments associated with given creator
     * @param educatorId creator id
     * @return Lesson Segment list
     */
    List<LessonSegment> getAllOfCreatorId(EducatorId educatorId);
}
