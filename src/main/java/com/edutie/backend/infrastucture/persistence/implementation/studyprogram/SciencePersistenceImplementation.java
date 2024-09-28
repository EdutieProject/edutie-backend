package com.edutie.backend.infrastucture.persistence.implementation.studyprogram;

import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import com.edutie.backend.domain.studyprogram.science.persistence.SciencePersistence;
import com.edutie.backend.infrastucture.persistence.PersistenceError;
import com.edutie.backend.infrastucture.persistence.jpa.repositories.ScienceRepository;
import validation.WrapperResult;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import lombok.*;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SciencePersistenceImplementation implements SciencePersistence {
	private final ScienceRepository scienceRepository;

	/**
	 * Override this to provide repository for default methods
	 *
	 * @return crud jpa repository
	 */
	@Override
	public JpaRepository<Science, ScienceId> getRepository() {
		return scienceRepository;
	}

	/**
	 * Override this to provide entity class for default methods
	 *
	 * @return class of persistence entity
	 */
	@Override
	public Class<Science> entityClass() {
		return Science.class;
	}

	/**
	 * Retrieve all sciences
	 *
	 * @return Science list
	 */
	@Override
	public WrapperResult<List<Science>> getAll() {
		try {
			List<Science> sciences = scienceRepository.findAll();
			return WrapperResult.successWrapper(sciences);
		} catch (Exception exception) {
			return WrapperResult.failureWrapper(PersistenceError.exceptionEncountered(exception));
		}
	}
}
