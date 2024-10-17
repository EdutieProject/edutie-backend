package com.edutie.backend.api.v1.learning;

import com.edutie.backend.api.common.ApiResult;
import com.edutie.backend.api.common.GenericRequestHandler;
import com.edutie.backend.application.learning.learningresource.*;
import com.edutie.backend.application.learning.learningresult.commands.AssessSolutionCommand;
import com.edutie.backend.application.learning.learningresource.commands.CreateLearningResourceCommand;
import com.edutie.backend.application.learning.learningresource.commands.CreateRandomFactDynamicLearningResourceCommand;
import com.edutie.backend.application.learning.learningresource.queries.GetLearningResourceByIdQuery;
import com.edutie.backend.application.learning.learningresource.queries.GetLearningResourcesByDefinitionIdQuery;
import com.edutie.backend.application.learning.learningresult.AssessSolutionCommandHandler;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.identities.LearningResourceId;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.infrastucture.authorization.student.StudentAuthorization;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/learning/learning-resource")
@RequiredArgsConstructor
@Tag(name = "Learning Resource Controller", description = "Provides operations regarding learning resources in the learning context")
public class LearningResourceController {
    private final StudentAuthorization studentAuthorization;
    private final GetLearningResourceByIdQueryHandler getLearningResourceByIdQueryHandler;
    private final GetLearningResourcesByDefinitionIdQueryHandler getLearningResourcesByDefinitionIdQueryHandler;
    private final CreateLearningResourceCommandHandler createLearningResourceCommandHandler;
    private final CreateRandomFactDynamicLearningResourceCommandHandler createRandomFactDynamicLearningResourceCommandHandler;
    private final AssessSolutionCommandHandler assessSolutionCommandHandler;

    @GetMapping
    @Operation(description = "Retrieves a learning resource by its identifier")
    public ResponseEntity<ApiResult<LearningResource>> getLearningResourceById(Authentication authentication, @RequestParam LearningResourceId learningResourceId) {
        return new GenericRequestHandler<LearningResource>()
                .authenticate(authentication)
                .authorize(studentAuthorization)
                .handle((userId) -> getLearningResourceByIdQueryHandler.handle(
                        new GetLearningResourceByIdQuery().learningResourceId(learningResourceId).studentUserId(userId)
                ));
    }

    @GetMapping("/of-definition")
    @Operation(description = "Retrieves all learning resources generated using a given definition.")
    public ResponseEntity<ApiResult<List<LearningResource>>> getLearningResourcesByDefinitionId(Authentication authentication, @RequestParam LearningResourceDefinitionId definitionId) {
        return new GenericRequestHandler<List<LearningResource>>()
                .authenticate(authentication)
                .authorize(studentAuthorization)
                .handle((userId) -> getLearningResourcesByDefinitionIdQueryHandler.handle(
                        new GetLearningResourcesByDefinitionIdQuery().studentUserId(userId).learningResourceDefinitionId(definitionId)
                ));
    }


    @PostMapping
    @Operation(description = "Creates a personalized learning resource for a student invoking the flow using the definition of id provided in the command.")
    public ResponseEntity<ApiResult<LearningResource>> createLearningResource(Authentication authentication, @RequestBody CreateLearningResourceCommand command) {
        return new GenericRequestHandler<LearningResource>()
                .authenticate(authentication)
                .authorize(studentAuthorization)
                .handle((userId) -> createLearningResourceCommandHandler.handle(
                        command.studentUserId(userId)
                ));
    }

    @PostMapping("/dynamic")
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

    @PostMapping("/assess-solution")
    @Operation(description = "Assesses the solution provided in the command and returns a Learning Result.")
    public ResponseEntity<ApiResult<LearningResult>> assessSolution(Authentication authentication, @RequestBody AssessSolutionCommand command) {
        return new GenericRequestHandler<LearningResult>()
                .authenticate(authentication)
                .authorize(studentAuthorization)
                .handle((userId) -> assessSolutionCommandHandler.handle(
                        command.studentUserId(userId)
                ));
    }
}
