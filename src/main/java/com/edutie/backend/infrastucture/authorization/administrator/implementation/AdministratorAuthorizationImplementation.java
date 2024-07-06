package com.edutie.backend.infrastucture.authorization.administrator.implementation;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.infrastucture.authorization.AuthorizationError;
import com.edutie.backend.infrastucture.authorization.administrator.AdministratorAuthorization;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.AdministratorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Result;

@Component
@RequiredArgsConstructor
public class AdministratorAuthorizationImplementation implements AdministratorAuthorization {
    private final AdministratorRepository administratorRepository;

    @Override
    public Result authorize(UserId userId) {
        return administratorRepository.findByOwnerUserId(userId).isPresent() ?
                Result.success() : Result.failure(AuthorizationError.roleExpected(Administrator.class));
    }
}
