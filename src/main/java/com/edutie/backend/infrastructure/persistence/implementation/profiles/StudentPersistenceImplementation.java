package com.edutie.backend.infrastructure.persistence.implementation.profiles;

import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.identities.StudentId;
import com.edutie.backend.domain.personalization.student.persistence.StudentPersistence;
import com.edutie.backend.infrastructure.persistence.jpa.repositories.StudentRepository;
import com.edutie.backend.infrastructure.persistence.jpa.repositories.common.RoleRepository;
import org.springframework.stereotype.*;
import lombok.*;

@Component
@RequiredArgsConstructor
public class StudentPersistenceImplementation implements StudentPersistence {
	private final StudentRepository studentRepository;

	/**
	 * Override this to provide repository for default methods
	 *
	 * @return crud jpa repository
	 */
	@Override
	public RoleRepository<Student, StudentId> getRepository() {
		return studentRepository;
	}

	/**
	 * Override this to provide entity class for default methods
	 *
	 * @return class of persistence entity
	 */
	@Override
	public Class<Student> entityClass() {
		return Student.class;
	}
}
