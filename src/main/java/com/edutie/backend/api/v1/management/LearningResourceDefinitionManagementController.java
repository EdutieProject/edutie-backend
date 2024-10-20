package com.edutie.backend.api.v1.management;

import com.edutie.backend.api.common.ApiResult;
import com.edutie.backend.api.common.GenericRequestHandler;
import com.edutie.backend.application.management.learningresourcedefinition.CreateLearningResourceDefinitionCommandHandler;
import com.edutie.backend.application.management.learningresourcedefinition.commands.CreateLearningResourceDefinitionCommand;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.infrastucture.authorization.educator.EducatorAuthorization;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/management/learning-resource-definitions")
@RequiredArgsConstructor
@Tag(name = "Learning Resource Definition Management Controller", description = "Provides operations regarding learning res. definitions in the management context")
public class LearningResourceDefinitionManagementController {
    private final EducatorAuthorization educatorAuthorization;
    private final CreateLearningResourceDefinitionCommandHandler createLearningResourceDefinitionCommandHandler;

    @PostMapping("/create")
    @Operation(description = "Creates learning resource definition. May be performed only by an educator")
    public ResponseEntity<ApiResult<LearningResourceDefinition>> createLearningResourceDefinition(Authentication authentication, @RequestBody CreateLearningResourceDefinitionCommand command) {
        return new GenericRequestHandler<LearningResourceDefinition>()
                .authenticate(authentication)
                .authorize(educatorAuthorization)
                .handle(userId -> createLearningResourceDefinitionCommandHandler.handle(
                        command.educatorUserId(userId)
                ));
    }
}
