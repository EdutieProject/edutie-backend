package com.edutie.edutiebackend.application.services.management.learningresult;

import com.edutie.edutiebackend.application.services.common.GenericCrudService;
import com.edutie.edutiebackend.domain.core.learningresult.LearningResult;
import com.edutie.edutiebackend.domain.core.learningresult.identities.LearningResultId;
import com.edutie.edutiebackend.domain.core.student.identities.StudentId;

import java.util.Set;

public interface LearningResultService extends GenericCrudService<LearningResult, LearningResultId> {
    Set<LearningResult> getAllOfStudent(StudentId studentId);
}
