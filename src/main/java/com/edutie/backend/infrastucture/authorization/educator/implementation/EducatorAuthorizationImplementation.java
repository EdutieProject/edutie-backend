package com.edutie.backend.infrastucture.authorization.educator.implementation;

import com.edutie.backend.domain.common.base.EntityBase;
import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.infrastucture.authorization.educator.EducatorAuthorization;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class EducatorAuthorizationImplementation implements EducatorAuthorization {
    private final EducatorPersistence educatorPersistence;

    @Override
    public WrapperResult<EducatorId> authorize(UserId userId) {
        WrapperResult<Educator> educatorResult = educatorPersistence.getByUserId(userId);
        return educatorResult.map(EntityBase::getId);
    }
}
