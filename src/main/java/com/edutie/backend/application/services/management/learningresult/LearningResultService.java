package com.edutie.backend.application.services.management.learningresult;

import java.util.Set;

import com.edutie.backend.application.services.common.servicebase.GenericCrudService;
import com.edutie.backend.domain.learner.learningresult.LearningResult;
import com.edutie.backend.domain.learner.learningresult.identities.LearningResultId;
import com.edutie.backend.domain.learner.student.identities.StudentId;

public interface LearningResultService extends GenericCrudService<LearningResult, LearningResultId> {
    /**
     * @param studentId
     * @return
     */
    Set<LearningResult> getAllStudents(StudentId studentId);
}
