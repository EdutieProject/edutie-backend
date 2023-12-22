package com.edutie.edutiebackend.application.services.management.learningresult;

import java.util.Set;

import com.edutie.edutiebackend.application.services.common.servicebase.GenericCrudService;
import com.edutie.edutiebackend.domain.core.learningresult.LearningResult;
import com.edutie.edutiebackend.domain.core.learningresult.identities.LearningResultId;
import com.edutie.edutiebackend.domain.core.student.identities.StudentId;

public interface LearningResultService extends GenericCrudService<LearningResult, LearningResultId> {
    /**
     * @param studentId
     * @return
     */
    Set<LearningResult> getAllStudents(StudentId studentId);
}
