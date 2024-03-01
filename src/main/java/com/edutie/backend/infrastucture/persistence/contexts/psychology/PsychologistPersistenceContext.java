package com.edutie.backend.infrastucture.persistence.contexts.psychology;

import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.education.psychologist.Psychologist;
import com.edutie.backend.domain.education.psychologist.identities.PsychologistId;
import com.edutie.backend.infrastucture.persistence.contexts.base.PersistenceContext;

import java.util.Optional;

public interface PsychologistPersistenceContext extends PersistenceContext<Psychologist, PsychologistId> {
    /**
     * Retrieve psychologist associated with given user. Optional is empty when there is no psychologist
     * profile associated
     * @param userId user id
     * @return Optional Psychologist
     */
    Optional<Psychologist> getByUserId(UserId userId);
}
