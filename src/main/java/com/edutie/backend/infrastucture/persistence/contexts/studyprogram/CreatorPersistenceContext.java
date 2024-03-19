package com.edutie.backend.infrastucture.persistence.contexts.studyprogram;

import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.infrastucture.persistence.contexts.base.PersistenceContext;

import java.util.Optional;

public interface CreatorPersistenceContext extends PersistenceContext<Educator, EducatorId> {
    /**
     * Retrieves the creator associated with given user
     * @param userId user id
     * @return Optional Creator
     */
    Optional<Educator> getByUserId(UserId userId);
}
