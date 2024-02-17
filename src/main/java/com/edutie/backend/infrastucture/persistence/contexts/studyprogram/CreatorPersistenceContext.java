package com.edutie.backend.infrastucture.persistence.contexts.studyprogram;

import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.studyprogram.creator.Creator;
import com.edutie.backend.domain.studyprogram.creator.identities.CreatorId;
import com.edutie.backend.infrastucture.persistence.contexts.base.PersistenceContext;

import java.util.Optional;

public interface CreatorPersistenceContext extends PersistenceContext<Creator, CreatorId> {
    /**
     * Retrieves the creator associated with given user
     * @param userId user id
     * @return Optional Creator
     */
    Optional<Creator> getByUserId(UserId userId);
}
