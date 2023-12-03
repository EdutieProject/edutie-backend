package com.edutie.edutiebackend.application.services.ports.management;

import com.edutie.edutiebackend.application.services.ports.common.GenericCrudService;
import com.edutie.edutiebackend.domain.core.lesson.identities.LessonId;
import com.edutie.edutiebackend.domain.core.lessonsegment.LessonSegment;
import com.edutie.edutiebackend.domain.core.lessonsegment.identities.LessonSegmentId;

import java.util.Set;

public interface LessonSegmentService extends GenericCrudService<LessonSegment, LessonSegmentId> {
    Set<LessonSegment> getAllOfLesson(LessonId lessonId);
    LessonSegment assignPrevSegment(LessonSegmentId lessonSegmentId, LessonSegmentId previousSegmentId);
    LessonSegment assignNextSegment(LessonSegmentId lessonSegmentId, LessonSegmentId nextSegmentId);
    LessonSegment assignNextSegments(LessonSegmentId lessonSegmentId, Set<LessonSegmentId> nextSegmentIds);
    LessonSegment createAsNext(LessonSegment lessonSegment, LessonSegmentId prevLessonSegmentId);
    LessonSegment createInLesson(LessonSegment lessonSegment, LessonId lessonId);
}
