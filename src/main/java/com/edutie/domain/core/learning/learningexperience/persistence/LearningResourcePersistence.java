package com.edutie.domain.core.learning.learningexperience.persistence;

import com.edutie.domain.core.common.persistence.Persistence;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.identities.LearningResourceId;
import com.edutie.domain.core.learning.student.identities.StudentId;
import validation.WrapperResult;

import java.util.List;

public interface LearningResourcePersistence extends Persistence<LearningExperience, LearningResourceId> {
    /**
     * Retrieves latest learning experiences made for the student.
     *
     * @param studentId student id
     * @return Wrapper result of desired list
     */
    WrapperResult<List<LearningExperience>> getLatestForStudent(StudentId studentId);
}
