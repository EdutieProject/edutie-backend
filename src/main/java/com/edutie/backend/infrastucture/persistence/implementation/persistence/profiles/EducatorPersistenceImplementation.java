package com.edutie.backend.infrastucture.persistence.implementation.persistence.profiles;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.EducatorRepository;
import com.edutie.backend.infrastucture.persistence.implementation.persistence.common.PersistenceError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Result;
import validation.WrapperResult;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EducatorPersistenceImplementation implements EducatorPersistence {
    private final EducatorRepository educatorRepository;
    /**
     * Retrieves the entity using its identifier. Entity is wrapped in optional, therefore
     * it might not be present based on the identifier presence in the database.
     *
     * @param educatorId entity id
     * @return Optional entity
     */
    @Override
    public WrapperResult<Educator> getById(EducatorId educatorId) {
        try {
            Optional<Educator> optionalEducator = educatorRepository.findById(educatorId);
            return optionalEducator
                    .map(Result::successWrapper)
                    .orElseGet(() -> Result.failureWrapper(PersistenceError.notFound(Educator.class)));
        } catch (Exception exception) {
            return Result.failureWrapper(PersistenceError.exceptionEncountered(exception));
        }
    }

    /**
     * Removes the entity of the provided id from the database. If the entity is not preset or could not be
     * deleted, does nothing and returns failure result.
     *
     * @param educatorId entity id
     * @return Result object
     */
    @Override
    public Result removeById(EducatorId educatorId) {
        try {
            educatorRepository.deleteById(educatorId);
            return Result.success();
        } catch (Exception exception) {
            return Result.failure(PersistenceError.exceptionEncountered(exception));
        }
    }

    /**
     * Retrieves role profile for the given user. The presence of the role profile should be checked
     * using authorization before using this function.
     *
     * @param userId id of a user
     * @return Role profile of a user
     */
    @Override
    public Educator getByUserId(UserId userId) {
        List<Educator> educator = educatorRepository.findEducatorsByOwnerUserId(userId);
        return educator.stream().findFirst().get();
    }

    /**
     * Persists the provided role profile into the database. If it is already present,
     * updates its state to the provided object's state. Returns result indicating whether
     * the operation was successful or not
     *
     * @param educator
     * @return Result object
     */
    @Override
    public Result save(Educator educator) {
        try {
            educatorRepository.save(educator);
            return Result.success();
        } catch (Exception exception) {
            return Result.failure(PersistenceError.exceptionEncountered(exception));
        }
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
            Educator educator = educatorRepository.findEducatorsByOwnerUserId(userId).getFirst();
            educatorRepository.delete(educator);
            return Result.success();
        } catch (NoSuchElementException ignored) {
            return Result.failure(PersistenceError.notFound(Educator.class));
        } catch (Exception exception) {
            return Result.failure(PersistenceError.exceptionEncountered(exception));
        }
    }
}
