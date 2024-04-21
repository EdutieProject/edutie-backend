package com.edutie.backend.domain.learner.learningresult.persistence;

import com.edutie.backend.domain.learner.learningresult.LearningResult;
import com.edutie.backend.domain.learner.learningresult.identities.LearningResultId;
import com.edutie.backend.domain.learner.student.identities.StudentId;
import com.edutie.backend.domain.studyprogram.segment.identities.SegmentId;
import com.edutie.backend.domain.common.persistence.PersistenceBase;
import validation.WrapperResult;

import java.util.List;

public interface LearningResultPersistence extends PersistenceBase<LearningResult, LearningResultId> {
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
