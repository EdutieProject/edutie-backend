package com.edutie.backend.infrastucture.authorization.student.implementation;

import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.learner.student.identities.StudentId;
import com.edutie.backend.domain.learner.student.persistence.StudentPersistence;
import com.edutie.backend.infrastucture.authorization.student.StudentAuthorization;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Result;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class StudentAuthorizationImplementation implements StudentAuthorization {
    private final StudentPersistence studentPersistence;
    @Override
    public WrapperResult<StudentId> authorize(UserId userId) {
        return Result.successWrapper(studentPersistence.getByUserId(userId).getId());
    }
}
