package com.edutie.backend.domain.learner.learningresult.persistence;

import com.edutie.backend.domain.learner.learningresult.LearningResult;
import com.edutie.backend.domain.learner.learningresult.identities.LearningResultId;
import com.edutie.backend.domain.learner.student.identities.StudentId;
import com.edutie.backend.domain.studyprogram.lessonsegment.identities.LessonSegmentId;
import com.edutie.backend.domain.common.persistence.PersistenceBase;

import java.util.List;

public interface LearningResultPersistence extends PersistenceBase<LearningResult, LearningResultId> {
    /**
     * Retrieve all learning results associated with given lesson segment
     * @param lessonSegmentId lesson segment identifier
     * @return Learning Result list
     */
    List<LearningResult> getAllOfLessonSegmentId(LessonSegmentId lessonSegmentId);

    /**
     * Retrieve all learning results associated with given student
     * @param studentId student identifier
     * @return Learning Result list
     */
    List<LearningResult> getAllOfStudentId(StudentId studentId);
}
