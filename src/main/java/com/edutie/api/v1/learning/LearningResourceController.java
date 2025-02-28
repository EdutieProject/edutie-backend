package com.edutie.api.v1.learning;

import com.edutie.api.common.ApiResult;
import com.edutie.api.common.GenericRequestHandler;
import com.edutie.application.learning.learningresource.*;
import com.edutie.application.learning.learningresource.commands.CreateDynamicLearningResourceCommand;
import com.edutie.application.learning.learningresource.commands.CreateLearningResourceCommand;
import com.edutie.application.learning.learningresource.commands.CreateSimilarLearningResourceCommand;
import com.edutie.application.learning.learningresource.queries.GetLatestLearningResourcesForStudentQuery;
import com.edutie.application.learning.learningresource.queries.GetLearningResourceByIdQuery;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.identities.LearningExperienceId;
import com.edutie.infrastructure.authorization.student.StudentAuthorization;
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
    private final GetLatestLearningResourcesForStudentQueryHandler getLatestLearningResourcesForStudentQueryHandler;
    private final CreateLearningResourceCommandHandler createLearningResourceCommandHandler;
    private final CreateDynamicLearningResourceCommandHandler createDynamicLearningResourceCommandHandler;
    private final CreateSimilarLearningResourceCommandHandler createSimilarLearningResourceCommandHandler;

    @GetMapping("/{learningExperienceId}")
    @Operation(description = "Retrieves a learning resource by its identifier")
    public ResponseEntity<ApiResult<LearningExperience>> getLearningResourceById(Authentication authentication, @PathVariable LearningExperienceId learningExperienceId) {
        return new GenericRequestHandler<LearningExperience>()
                .authenticate(authentication)
                .authorize(studentAuthorization)
                .handle((userId) -> getLearningResourceByIdQueryHandler.handle(
                        new GetLearningResourceByIdQuery().learningExperienceId(learningExperienceId).studentUserId(userId)
                ));
    }

    @GetMapping("/get-latest")
    @Operation(description = "Retrieves latest learning resources for student invoking the flow")
    public ResponseEntity<ApiResult<List<LearningExperience>>> getLatestLearningResourcesForStudent(Authentication authentication) {
        return new GenericRequestHandler<List<LearningExperience>>()
                .authenticate(authentication)
                .authorize(studentAuthorization)
                .handle((userId) -> getLatestLearningResourcesForStudentQueryHandler.handle(
                        new GetLatestLearningResourcesForStudentQuery().studentUserId(userId)
                ));
    }


    @PostMapping("/create-from-definition")
    @Operation(description = "Creates a personalized learning resource for a student invoking the flow using the definition of id provided in the command.")
    public ResponseEntity<ApiResult<LearningExperience>> createLearningResource(Authentication authentication, @RequestBody CreateLearningResourceCommand command) {
        return new GenericRequestHandler<LearningExperience>()
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
    public ResponseEntity<ApiResult<LearningExperience>> createRandomFactDynamicLearningResource(Authentication authentication,
                                                                                                 @RequestBody CreateDynamicLearningResourceCommand command) {
        return new GenericRequestHandler<LearningExperience>()
                .authenticate(authentication)
                .authorize(studentAuthorization)
                .handle((userId) -> createDynamicLearningResourceCommandHandler.handle(
                        command.studentUserId(userId)
                ));
    }

    @PostMapping("/create-similar")
    @Operation(description = """
            Creates a similar learning resource to the one which identifier is provided in the command. Works only for
            resources with static definition.
            """)
    public ResponseEntity<ApiResult<LearningExperience>> createSimilarLearningResource(Authentication authentication,
                                                                                       @RequestBody CreateSimilarLearningResourceCommand command) {
        return new GenericRequestHandler<LearningExperience>()
                .authenticate(authentication)
                .authorize(studentAuthorization)
                .handle((userId) -> createSimilarLearningResourceCommandHandler.handle(
                        command.studentUserId(userId)
                ));
    }
}
