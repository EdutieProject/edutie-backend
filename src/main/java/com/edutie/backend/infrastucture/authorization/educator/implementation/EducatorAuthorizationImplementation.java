package com.edutie.backend.infrastucture.authorization.educator.implementation;

import com.edutie.backend.domain.common.base.EntityBase;
import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.infrastucture.authorization.educator.EducatorAuthorization;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.EducatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Error;
import validation.Result;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class EducatorAuthorizationImplementation implements EducatorAuthorization {
    private final EducatorRepository educatorRepository;
    @Override
    public Result authorize(UserId userId) {
        return educatorRepository.findEducatorsByCreatedBy(userId).isEmpty() ?
                Result.failure(new Error("AUTHORIZATION", "Expected Educator role for this user"))
                : Result.success();
    }
}
