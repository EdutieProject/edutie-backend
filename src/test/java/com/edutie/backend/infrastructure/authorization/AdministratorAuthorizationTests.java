package com.edutie.backend.infrastructure.authorization;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.administration.administrator.persistence.AdministratorPersistence;
import com.edutie.backend.infrastructure.authorization.administrator.AdministratorAuthorization;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.*;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class AdministratorAuthorizationTests {
	private final UserId userId = new UserId(UUID.fromString("6f3ed855-4716-4f8e-a42e-adf7a0c3273c"));
	@Autowired
	AdministratorAuthorization administratorAuthorization;
	@Autowired
	AdministratorPersistence administratorPersistence;

	@Test
	public void authorizeFailureTest() {
		assert administratorAuthorization.authorize(new UserId()).isFailure();
	}

	@Test
	public void roleInjectionSuccessTest() {
		var jwt = Jwt.withTokenValue("token").header("alg", "none").claim("sub", userId.identifierValue().toString()).build();
		var authorities = AuthorityUtils.createAuthorityList("edutie-admin");
		JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(null, null);
		administratorAuthorization.injectRoles(jwtAuthenticationToken);

		assert administratorPersistence.getByAuthorizedUserId(userId) != null;
	}

	@Test
	public void roleInjectionFailureTest() {
		var jwt = Jwt.withTokenValue("token").header("alg", "none").claim("sub", userId.identifierValue().toString()).build();
		JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(null);
		administratorAuthorization.injectRoles(jwtAuthenticationToken);

		assertThrows(Exception.class, () -> administratorPersistence.getByAuthorizedUserId(userId));
	}

}
