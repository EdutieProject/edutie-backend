package com.edutie.infrastructure.authorization;

import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.education.educator.persistence.EducatorPersistence;
import com.edutie.infrastructure.authorization.educator.EducatorAuthorization;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.*;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class EducatorAuthorizationTests {
	private final UserId userId = new UserId(UUID.fromString("6f3ed855-4716-4f8e-a42e-adf7a0c3273c"));
	@Autowired
	EducatorAuthorization educatorAuthorization;
	@Autowired
	EducatorPersistence educatorPersistence;

	@Test
	public void authorizeFailureTest() {
		assert educatorAuthorization.authorize(new UserId()).isFailure();
	}

	@Test
	public void roleInjectionSuccessTest() {
		var jwt = Jwt.withTokenValue("token").header("alg", "none").claim("sub", userId.identifierValue().toString()).build();
		var authorities = AuthorityUtils.createAuthorityList("edutie-admin");
		JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(null, null);
		educatorAuthorization.injectRoles(jwtAuthenticationToken);

		assert educatorPersistence.getByAuthorizedUserId(userId) != null;
	}

	@Test
	public void roleInjectionFailureTest() {
		var jwt = Jwt.withTokenValue("token").header("alg", "none").claim("sub", userId.identifierValue().toString()).build();
		JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(null);
		educatorAuthorization.injectRoles(jwtAuthenticationToken);

		assertThrows(Exception.class, () -> educatorPersistence.getByAuthorizedUserId(userId));
	}

}
