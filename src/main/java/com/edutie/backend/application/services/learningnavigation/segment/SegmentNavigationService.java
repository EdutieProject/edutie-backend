package com.edutie.backend.application.services.learningnavigation.segment;

import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import com.edutie.backend.domain.studyprogram.lessonsegment.identities.LessonSegmentId;
import com.edutie.backend.domain.learner.student.identities.StudentId;

import java.util.Set;

public interface SegmentNavigationService {
    /**
     * @param studentId
     * @param lessonId
     * @return
     */
    Set<LessonSegmentId> getBestLearningPathFor(StudentId studentId, LessonId lessonId); //Tutaj dorzuciłbym coś na zasadzie, że jest najlepsza ścieżka sugerowana (i na stronie pokazać to boldowane) ale oprócz tego są 2 ścieżki na tzw.wszelki wypadak, tylko nie wiem jak to opisaćw interfejsie
    /**
     * @param studentId
     * @param lessonId
     * @return
     */
    LessonSegmentId getBestAvailableSegmentFor(StudentId studentId, LessonId lessonId);
}
