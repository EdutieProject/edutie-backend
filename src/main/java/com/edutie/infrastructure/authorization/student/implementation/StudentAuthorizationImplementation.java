package com.edutie.infrastructure.authorization.student.implementation;

import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.infrastructure.authorization.AuthorizationError;
import com.edutie.infrastructure.authorization.student.StudentAuthorization;
import com.edutie.infrastructure.persistence.implementation.profiles.repositories.StudentRepository;
import validation.Result;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.*;
import org.springframework.stereotype.*;
import lombok.*;
import lombok.extern.slf4j.*;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class StudentAuthorizationImplementation implements StudentAuthorization {
	private final StudentRepository studentRepository;

	@Override
	public Result authorize(UserId userId) {
		return studentRepository.findByOwnerUserId(userId).isEmpty() ? Result.failure(AuthorizationError.roleExpected(Student.class)) : Result.success();
	}

	/**
	 * Pre-inject roles if any injectable exist in authentication token.
	 *
	 * @param authentication authentication token
	 */
	@Override
	public synchronized void injectRoles(JwtAuthenticationToken authentication) {
		UserId userId = new UserId(UUID.fromString(authentication.getTokenAttributes().get(JwtClaimNames.SUB).toString()));
		if (studentRepository.findByOwnerUserId(userId).isEmpty()) {
			log.info("No student role profile found in edutie context. Injecting student role in the edutie db.");
			Student studentProfile = Student.create(userId);
			studentRepository.save(studentProfile);
			return;
		}
		log.debug("Student role already exits. Not injecting student role for this request.");
	}
}
