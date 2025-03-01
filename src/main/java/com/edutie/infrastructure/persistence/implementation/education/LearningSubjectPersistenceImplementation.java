package com.edutie.infrastructure.persistence.implementation.education;

import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.identities.LearningSubjectId;
import com.edutie.domain.core.education.learningsubject.persistence.LearningSubjectPersistence;
import com.edutie.infrastructure.persistence.jpa.repositories.LearningSubjectRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import lombok.*;

@Component
@RequiredArgsConstructor
public class LearningSubjectPersistenceImplementation implements LearningSubjectPersistence {
	private final LearningSubjectRepository learningSubjectRepository;

	/**
	 * Override this to provide repository for default methods
	 *
	 * @return crud jpa repository
	 */
	@Override
	public JpaRepository<LearningSubject, LearningSubjectId> getRepository() {
		return learningSubjectRepository;
	}

	/**
	 * Override this to provide entity class for default methods
	 *
	 * @return class of persistence entity
	 */
	@Override
	public Class<LearningSubject> entityClass() {
		return LearningSubject.class;
	}

}
