package com.edutie.backend.domain.personalization.learningresult.persistence;

import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.identities.LearningResultId;
import com.edutie.backend.domain.personalization.student.identities.StudentId;
import com.edutie.backend.domain.studyprogram.segment.identities.SegmentId;
import com.edutie.backend.domain.common.persistence.Persistence;
import validation.WrapperResult;

import java.util.List;

public interface LearningResultPersistence extends Persistence<LearningResult, LearningResultId> {
    /**
     * Retrieve all learning results associated with given lesson segment
     * @param segmentId lesson segment identifier
     * @return Wrapper result of desired list
     */
    WrapperResult<List<LearningResult>> getAllOfLessonSegmentId(SegmentId segmentId);

    /**
     * Retrieve all learning results associated with given student
     * @param studentId student identifier
     * @return Wrapper result of desired list
     */
    WrapperResult<List<LearningResult>> getAllOfStudentId(StudentId studentId);
}
