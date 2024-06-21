package com.edutie.backend.infrastucture.persistence.implementation.persistence.profiles;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.EducatorRepository;
import com.edutie.backend.infrastucture.persistence.implementation.persistence.common.PersistenceError;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import validation.Result;

import java.util.NoSuchElementException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EducatorPersistenceImplementation implements EducatorPersistence {
    private final EducatorRepository educatorRepository;

    /**
     * Override this to provide repository for default methods
     *
     * @return crud jpa repository
     */
    @Override
    public JpaRepository<Educator, EducatorId> getRepository() {
        return educatorRepository;
    }

    /**
     * Override this to provide entity class for default methods
     *
     * @return class of persistence entity
     */
    @Override
    public Class<Educator> entityClass() {
        return Educator.class;
    }

    /**
     * Retrieves role profile for the given user. The presence of the role profile should be checked
     * using authorization before using this function.
     *
     * @param userId id of a user
     * @return Role profile of a user
     */
    @Override
    public Educator getByAuthorizedUserId(UserId userId) {
        return educatorRepository.findByOwnerUserId(userId).get();
    }

    /**
     * Removes the role for the user of specified id from the database. Success is returned even
     * when the role is not present at first in the database. Failure is returned only when something goes
     * wrong
     *
     * @param userId entity id
     * @return Result object
     */
    @Override
    public Result removeForUser(UserId userId) {
        try {
            Educator educator = educatorRepository.findByOwnerUserId(userId).get();
            educatorRepository.delete(educator);
            return Result.success();
        } catch (NoSuchElementException ignored) {
            return Result.failure(PersistenceError.notFound(Educator.class));
        } catch (Exception exception) {
            return Result.failure(PersistenceError.exceptionEncountered(exception));
        }
    }
}
