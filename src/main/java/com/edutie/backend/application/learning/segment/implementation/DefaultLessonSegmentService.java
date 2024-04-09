package com.edutie.backend.application.learning.segment.implementation;

import com.edutie.backend.application.learning.segment.SegmentService;
import com.edutie.backend.application.learning.segment.viewmodels.SegmentView;
import com.edutie.backend.domain.learner.student.identities.StudentId;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import com.edutie.backend.domain.studyprogram.lessonsegment.LessonSegment;
import com.edutie.backend.domain.studyprogram.lessonsegment.persistence.LessonSegmentPersistence;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DefaultLessonSegmentService implements SegmentService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final LessonSegmentPersistence lessonSegmentPersistence;

    /**
     * Retrieves a list of segment views for a specific lesson and student.
     *
     * @param lessonId  The identifier of the lesson.
     * @param studentId The identifier of the student.
     * @return A list of segment views for the specified lesson and student.
     */
    @Override
    public List<SegmentView> getSegmentsOfLessonForStudent(LessonId lessonId, StudentId studentId) {
        List<LessonSegment> segments = lessonSegmentPersistence.getAllOfLessonId(lessonId);
        //TODO compute "done" property in Segment Views. Perhaps add mappedBy relationship to parent entity in learning trees.
        return segments.stream().map(o -> new SegmentView(o, false)).collect(Collectors.toList());
    }
}
