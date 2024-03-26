package com.edutie.backend.domain.education.educator.persistence;

import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.common.persistence.PersistenceBase;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import validation.WrapperResult;

public interface EducatorPersistence extends PersistenceBase<Educator, EducatorId> {
    /**
     * Retrieves the creator associated with given user
     *
     * @param userId user id
     * @return Optional Creator
     */
    WrapperResult<Educator> getByUserId(UserId userId);
}
