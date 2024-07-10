package com.edutie.backend.domain.personalization.learningresource.persistence;

import com.edutie.backend.domain.personalization.student.identities.StudentId;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.identities.LearningResourceId;
import com.edutie.backend.domain.studyprogram.segment.identities.SegmentId;
import com.edutie.backend.domain.common.persistence.Persistence;
import validation.WrapperResult;

import java.util.List;

public interface LearningResourcePersistence extends Persistence<LearningResource, LearningResourceId> {
    /**
     * Retrieve all learning resources associated with given lesson segment
     * @param segmentId lesson segment id
     * @return Wrapper result of desired list
     */
    WrapperResult<List<LearningResource>> getAllOfLessonSegmentId(SegmentId segmentId);

    /**
     * Retrieve all Learning Resources associated with given student
     * @param studentId student id
     * @return Wrapper result of desired list
     */
    WrapperResult<List<LearningResource>> getAllOfStudentId(StudentId studentId);
}
