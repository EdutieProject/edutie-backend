package com.edutie.backend.api.v1.management;

import com.edutie.backend.api.common.ApiResult;
import com.edutie.backend.api.common.GenericRequestHandler;
import com.edutie.backend.application.management.learningresourcedefinition.CreateLearningResourceDefinitionCommandHandler;
import com.edutie.backend.application.management.learningresourcedefinition.commands.CreateLearningResourceDefinitionCommand;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.infrastucture.authorization.educator.EducatorAuthorization;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.*;
import org.springframework.security.core.*;
import org.springframework.web.bind.annotation.*;
import lombok.*;

@RestController
@RequestMapping("/api/v1/management/learning-resource")
@RequiredArgsConstructor
@Tag(name = "Learning Resource Definition Management Controller", description = "Provides operations regarding learning res. definitions in the management context")
public class LearningResourceDefinitionManagementController {
	private final EducatorAuthorization educatorAuthorization;
	private final CreateLearningResourceDefinitionCommandHandler createLearningResourceDefinitionCommandHandler;

	@PostMapping("/definitions")
	public ResponseEntity<ApiResult<LearningResourceDefinition>> createLearningResource(Authentication authentication, @RequestBody CreateLearningResourceDefinitionCommand command) {
		return new GenericRequestHandler<LearningResourceDefinition>().authenticate(authentication).authorize(educatorAuthorization).handle(userId -> createLearningResourceDefinitionCommandHandler.handle(command.educatorUserId(userId)));
	}
}
