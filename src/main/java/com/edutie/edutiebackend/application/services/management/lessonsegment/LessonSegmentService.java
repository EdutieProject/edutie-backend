package com.edutie.edutiebackend.application.services.management.lessonsegment;

import java.util.Set;

import com.edutie.edutiebackend.application.services.common.servicebase.GenericCrudService;
import com.edutie.edutiebackend.domain.core.lesson.identities.LessonId;
import com.edutie.edutiebackend.domain.core.lessonsegment.LessonSegment;
import com.edutie.edutiebackend.domain.core.lessonsegment.identities.LessonSegmentId;

public interface LessonSegmentService extends GenericCrudService<LessonSegment, LessonSegmentId> {
    /**
     * @param lessonId
     * @return
     */
    Set<LessonSegment> getAllOfLesson(LessonId lessonId);
    /**
     * @param lessonSegmentId
     * @param previousSegmentId
     * @return
     */
    LessonSegment assignPrevSegment(LessonSegmentId lessonSegmentId, LessonSegmentId previousSegmentId);
    /**
     * @param lessonSegmentId
     * @param nextSegmentId
     * @return
     */
    LessonSegment assignNextSegment(LessonSegmentId lessonSegmentId, LessonSegmentId nextSegmentId);
    /**
     * @param lessonSegmentId
     * @param nextSegmentIds
     * @return
     */
    LessonSegment assignNextSegments(LessonSegmentId lessonSegmentId, Set<LessonSegmentId> nextSegmentIds);
    /**
     * @param lessonSegment
     * @param prevLessonSegmentId
     * @return
     */
    LessonSegment createAsNext(LessonSegment lessonSegment, LessonSegmentId prevLessonSegmentId);
    /**
     * @param lessonSegment
     * @param lessonId
     * @return
     */
    LessonSegment createInLesson(LessonSegment lessonSegment, LessonId lessonId);
}

/*To samo co wcześniej. Są jakieś assigny, które można opisać jedną metodą.
 Poza tym są assigny z next i preview wiec można chyba dać listę itd.
*/