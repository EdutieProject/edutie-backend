package com.edutie.backend.api.v1.learning;

import com.edutie.backend.api.common.ApiResult;
import com.edutie.backend.api.common.GenericRequestHandler;
import com.edutie.backend.application.learning.learningresource.*;
import com.edutie.backend.application.learning.learningresource.commands.CreateLearningResourceCommand;
import com.edutie.backend.application.learning.learningresource.commands.CreateRandomFactDynamicLearningResourceCommand;
import com.edutie.backend.application.learning.learningresource.queries.GetLearningResourceByIdQuery;
import com.edutie.backend.application.learning.learningresource.queries.GetLearningResourcesByDefinitionIdQuery;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.identities.LearningResourceId;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.backend.infrastucture.authorization.student.StudentAuthorization;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/learning/resources")
@RequiredArgsConstructor
@Tag(name = "Learning Resource Controller", description = "Provides operations regarding learning resources in the learning context")
public class LearningResourceController {
    private final StudentAuthorization studentAuthorization;
    private final GetLearningResourceByIdQueryHandler getLearningResourceByIdQueryHandler;
    private final GetLearningResourcesByDefinitionIdQueryHandler getLearningResourcesByDefinitionIdQueryHandler;
    private final CreateLearningResourceCommandHandler createLearningResourceCommandHandler;
    private final CreateRandomFactDynamicLearningResourceCommandHandler createRandomFactDynamicLearningResourceCommandHandler;

    @GetMapping("/{learningResourceId}")
    @Operation(description = "Retrieves a learning resource by its identifier")
    public ResponseEntity<ApiResult<LearningResource>> getLearningResourceById(Authentication authentication, @PathVariable LearningResourceId learningResourceId) {
        return new GenericRequestHandler<LearningResource>()
                .authenticate(authentication)
                .authorize(studentAuthorization)
                .handle((userId) -> getLearningResourceByIdQueryHandler.handle(
                        new GetLearningResourceByIdQuery().learningResourceId(learningResourceId).studentUserId(userId)
                ));
    }

    @GetMapping("/retrieve-by-definition")
    @Operation(description = "Retrieves all learning resources generated using a given definition id.")
    public ResponseEntity<ApiResult<List<LearningResource>>> getLearningResourcesByDefinitionId(Authentication authentication, @RequestParam LearningResourceDefinitionId definitionId) {
        return new GenericRequestHandler<List<LearningResource>>()
                .authenticate(authentication)
                .authorize(studentAuthorization)
                .handle((userId) -> getLearningResourcesByDefinitionIdQueryHandler.handle(
                        new GetLearningResourcesByDefinitionIdQuery().studentUserId(userId).learningResourceDefinitionId(definitionId)
                ));
    }


    @PostMapping("/create-from-definition")
    @Operation(description = "Creates a personalized learning resource for a student invoking the flow using the definition of id provided in the command.")
    public ResponseEntity<ApiResult<LearningResource>> createLearningResource(Authentication authentication, @RequestBody CreateLearningResourceCommand command) {
        return new GenericRequestHandler<LearningResource>()
                .authenticate(authentication)
                .authorize(studentAuthorization)
                .handle((userId) -> createLearningResourceCommandHandler.handle(
                        command.studentUserId(userId)
                ));
    }

    @PostMapping("/create-dynamic")
    @Operation(description = """
            Creates a personalized learning resource for a student invoking the flow using the latest used definition 
            and altering the exercise to be about the provided random fact in the command.
            """)
    public ResponseEntity<ApiResult<LearningResource>> createRandomFactDynamicLearningResource(Authentication authentication,
                                                                                               @RequestBody CreateRandomFactDynamicLearningResourceCommand command) {
        return new GenericRequestHandler<LearningResource>()
                .authenticate(authentication)
                .authorize(studentAuthorization)
                .handle((userId) -> createRandomFactDynamicLearningResourceCommandHandler.handle(
                        command.studentUserId(userId)
                ));
    }
}
