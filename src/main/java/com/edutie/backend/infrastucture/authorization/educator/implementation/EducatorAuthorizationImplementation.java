package com.edutie.backend.infrastucture.authorization.educator.implementation;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.infrastucture.authorization.educator.EducatorAuthorization;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.EducatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Error;
import validation.Result;

@Component
@RequiredArgsConstructor
public class EducatorAuthorizationImplementation implements EducatorAuthorization {
    private final EducatorRepository educatorRepository;
    @Override
    public Result authorize(UserId userId) {
        return educatorRepository.findEducatorsByOwnerUserId(userId).isEmpty() ?
                Result.failure(new Error("AUTHORIZATION", "Expected Educator role for this user"))
                : Result.success();
    }
}
