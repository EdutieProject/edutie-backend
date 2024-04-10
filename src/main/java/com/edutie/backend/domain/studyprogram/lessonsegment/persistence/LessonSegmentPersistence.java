package com.edutie.backend.domain.studyprogram.lessonsegment.persistence;

import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import com.edutie.backend.domain.studyprogram.lessonsegment.Segment;
import com.edutie.backend.domain.studyprogram.lessonsegment.identities.LessonSegmentId;
import com.edutie.backend.domain.common.persistence.PersistenceBase;

import java.util.List;

public interface LessonSegmentPersistence extends PersistenceBase<Segment, LessonSegmentId> {
    /**
     * Retrieve all lesson segments associated with given lesson
     * @param lessonId lesson id
     * @return Lesson Segment list
     */
    List<Segment> getAllOfLessonId(LessonId lessonId);

    /**
     * Retrieve all lesson segments associated with given creator
     * @param educatorId creator id
     * @return Lesson Segment list
     */
    List<Segment> getAllOfCreatorId(EducatorId educatorId);
}
