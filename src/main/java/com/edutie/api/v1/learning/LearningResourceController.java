package com.edutie.api.v1.learning;

import com.edutie.api.common.ApiResult;
import com.edutie.api.common.GenericRequestHandler;
import com.edutie.application.learning.learningexperience.CreateSimilarLearningExperienceCommandHandler;
import com.edutie.application.learning.learningexperience.command.CreateSimilarLearningExperienceCommand;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.infrastructure.authorization.student.StudentAuthorization;
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
@RequestMapping("api/v1/learning-experience")
@RequiredArgsConstructor
@Tag(name = "Learning Resource Controller", description = "Provides operations regarding learning resources in the learning context")
public class LearningResourceController {
    private final StudentAuthorization studentAuthorization;
    private final CreateSimilarLearningExperienceCommandHandler createSimilarLearningExperienceCommandHandler;

    @PostMapping("/create-similar")
    @Operation(description = """
            Creates a similar learning resource to the one which identifier is provided in the command. Works only for
            resources with static definition.
            """)
    public ResponseEntity<ApiResult<LearningExperience<?>>> createSimilarLearningResource(Authentication authentication,
                                                                                          @RequestBody CreateSimilarLearningExperienceCommand command) {
        return new GenericRequestHandler<LearningExperience<?>>()
                .authenticate(authentication)
                .authorize(studentAuthorization)
                .handle((userId) -> createSimilarLearningExperienceCommandHandler.handle(
                        command.studentUserId(userId)
                ));
    }
}
