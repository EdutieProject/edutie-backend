package com.edutie.backend.infrastucture.authorization.student.implementation;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.infrastucture.authorization.student.StudentAuthorization;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Error;
import validation.Result;

@Component
@RequiredArgsConstructor
public class StudentAuthorizationImplementation implements StudentAuthorization {
    private final StudentRepository studentRepository;
    @Override
    public Result authorize(UserId userId) {
        return studentRepository.findStudentsByOwnerUserId(userId).isEmpty() ?
                Result.failure(new Error("AUTHORIZATION", "Expected Student role for this student"))
                : Result.success();
    }
}
