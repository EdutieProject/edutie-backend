package com.edutie.backend.infrastucture.authorization.student.implementation;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.learner.student.Student;
import com.edutie.backend.infrastucture.authorization.AuthorizationError;
import com.edutie.backend.infrastucture.authorization.student.StudentAuthorization;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Result;

@Component
@RequiredArgsConstructor
public class StudentAuthorizationImplementation implements StudentAuthorization {
    private final StudentRepository studentRepository;

    @Override
    public Result authorize(UserId userId) {
        return studentRepository.findByOwnerUserId(userId).isEmpty() ?
                Result.failure(AuthorizationError.roleExpected(Student.class))
                : Result.success();
    }
}
