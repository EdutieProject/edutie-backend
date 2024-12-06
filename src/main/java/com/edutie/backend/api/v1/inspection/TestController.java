package com.edutie.backend.api.v1.inspection;

import com.edutie.backend.api.common.*;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.infrastructure.authorization.administrator.AdministratorAuthorization;
import com.edutie.backend.infrastructure.authorization.educator.EducatorAuthorization;
import com.edutie.backend.infrastructure.authorization.student.StudentAuthorization;
import io.swagger.v3.oas.annotations.tags.Tag;
import validation.Result;
import validation.WrapperResult;
import org.springframework.http.*;
import org.springframework.security.core.*;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.*;
import org.springframework.web.bind.annotation.*;
import lombok.*;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/inspection")
@RequiredArgsConstructor
@Tag(name = "Inspection Controller", description = "Controller made for inspection purposes, such as healthcheck or authentication validity check.")
public class TestController {
	private final AdministratorAuthorization administratorAuthorization;
	private final EducatorAuthorization educatorAuthorization;
	private final StudentAuthorization studentAuthorization;

	@GetMapping("/healthcheck")
	public String healthcheck() {
		return "Healthy!";
	}

	@GetMapping("/test-authentication")
	public String authenticated(Authentication authentication) {
		if (authentication == null || !authentication.isAuthenticated()) {
			return Result.failure(AuthenticationError.invalidAuthentication()).toString();
		}
		if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {
			UserId userId = new UserId(UUID.fromString(jwtAuthenticationToken.getTokenAttributes().get(JwtClaimNames.SUB).toString()));
			String name = (String) jwtAuthenticationToken.getTokenAttributes().get("name");
			return "AUTH SUCCESS! Look, its you: " + userId + "\nYour name here: " + name;
		}
		return Result.failure(AuthenticationError.noJwtAuthentication()).toString();
	}

	@GetMapping("/test-roles")
	public String getRoles(Authentication authentication) {
		if (authentication == null || !authentication.isAuthenticated()) {
			return Result.failure(AuthenticationError.invalidAuthentication()).toString();
		}
		if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {
			return "AUTH SUCCESS! Look, your roles: " + jwtAuthenticationToken.getAuthorities().stream().map(o -> o.getAuthority() + ", ").collect(Collectors.joining());
		}
		return Result.failure(AuthenticationError.noJwtAuthentication()).toString();
	}

	@GetMapping("/authorization-student")
	public ResponseEntity<ApiResult<String>> studentEndpoint(Authentication authentication) {
		return new GenericRequestHandler<String>().authenticate(authentication).authorize(studentAuthorization).handle(() -> WrapperResult.successWrapper("Hello world!"));
	}


	@GetMapping("/authorization-educator")
	public ResponseEntity<ApiResult<String>> educatorEndpoint(Authentication authentication) {
		return new GenericRequestHandler<String>().authenticate(authentication).authorize(educatorAuthorization).handle(() -> WrapperResult.successWrapper("Hello world!"));
	}

	@GetMapping("/authorization-administrator")
	public ResponseEntity<ApiResult<String>> administratorEndpoint(Authentication authentication) {
		return new GenericRequestHandler<String>().authenticate(authentication).authorize(administratorAuthorization).handle(() -> WrapperResult.successWrapper("Hello world!"));
	}
}
