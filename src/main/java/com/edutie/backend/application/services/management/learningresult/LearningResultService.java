package com.edutie.backend.application.services.management.learningresult;

import java.util.Set;

import com.edutie.backend.application.services.common.servicebase.GenericCrudService;
import com.edutie.backend.domain.core.learningresult.LearningResult;
import com.edutie.backend.domain.core.learningresult.identities.LearningResultId;
import com.edutie.backend.domain.core.student.identities.StudentId;

public interface LearningResultService extends GenericCrudService<LearningResult, LearningResultId> {
    /**
     * @param studentId
     * @return
     */
    Set<LearningResult> getAllStudents(StudentId studentId);
}
