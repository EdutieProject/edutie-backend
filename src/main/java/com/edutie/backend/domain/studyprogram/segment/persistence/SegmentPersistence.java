package com.edutie.backend.domain.studyprogram.segment.persistence;

import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.domain.studyprogram.segment.identities.SegmentId;
import com.edutie.backend.domain.common.persistence.Persistence;
import validation.WrapperResult;

import java.util.List;

public interface SegmentPersistence extends Persistence<Segment, SegmentId> {
    /**
     * Retrieve all lesson segments associated with given lesson
     * @param lessonId lesson id
     * @return Lesson Segment list
     */
    WrapperResult<List<Segment>> getAllOfLessonId(LessonId lessonId);

    /**
     * Retrieve all lesson segments associated with given creator
     * @param educatorId educator id
     * @return Lesson Segment list
     */
    WrapperResult<List<Segment>> getAllOfEducatorId(EducatorId educatorId);
}
