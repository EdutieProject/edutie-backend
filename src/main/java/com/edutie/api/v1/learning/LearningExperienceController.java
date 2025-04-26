package com.edutie.api.v1.learning;

import com.edutie.api.common.ApiResult;
import com.edutie.api.common.GenericRequestHandler;
import com.edutie.application.learning.learningexperience.CreateLearningExperienceCommandHandler;
import com.edutie.application.learning.learningexperience.CreateSimilarLearningExperienceCommandHandler;
import com.edutie.application.learning.learningexperience.GetLearningExperienceByIdQueryHandler;
import com.edutie.application.learning.learningexperience.command.CreateLearningExperienceCommand;
import com.edutie.application.learning.learningexperience.command.CreateSimilarLearningExperienceCommand;
import com.edutie.application.learning.learningexperience.query.GetLearningExperienceByIdQuery;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.identities.LearningExperienceId;
import com.edutie.infrastructure.authorization.student.StudentAuthorization;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/learning-experience")
@RequiredArgsConstructor
@Tag(name = "Learning Experience Controller", description = "Provides operations regarding learning experiences in the learning context")
public class LearningExperienceController {
    private final StudentAuthorization studentAuthorization;
    private final CreateSimilarLearningExperienceCommandHandler createSimilarLearningExperienceCommandHandler;
    private final CreateLearningExperienceCommandHandler createLearningExperienceCommandHandler;
    private final GetLearningExperienceByIdQueryHandler getLearningExperienceByIdQueryHandler;

    @PostMapping("/create")
    @Operation(description = """
            Creates a personalized learning experience for the student user.
            """)
    public ResponseEntity<ApiResult<LearningExperience<?>>> createLearningExperience(Authentication authentication,
                                                                                     @RequestBody CreateLearningExperienceCommand command) {
        return new GenericRequestHandler<LearningExperience<?>>()
                .authenticate(authentication)
                .authorize(studentAuthorization)
                .handle((userId) -> createLearningExperienceCommandHandler.handle(
                        command.studentUserId(userId)
                ));
    }

    @GetMapping("/{learningExperienceId}")
    @Operation(description = """
            Retrieves a learning experience by its unique identifier.
            """)
    public ResponseEntity<ApiResult<LearningExperience<?>>> getLearningExperienceById(Authentication authentication,
                                                                                      @PathVariable LearningExperienceId learningExperienceId) {
        return new GenericRequestHandler<LearningExperience<?>>()
                .authenticate(authentication)
                .authorize(studentAuthorization)
                .handle((userId) -> getLearningExperienceByIdQueryHandler.handle(
                        new GetLearningExperienceByIdQuery()
                                .studentUserId(userId)
                                .learningExperienceId(learningExperienceId)
                ));
    }

    @PostMapping("/create-similar")
    @Operation(description = """
            Creates a learning experience similar to the one provided by its identifier.
            """)
    public ResponseEntity<ApiResult<LearningExperience<?>>> createSimilarLearningExperience(Authentication authentication,
                                                                                            @RequestBody CreateSimilarLearningExperienceCommand command) {
        return new GenericRequestHandler<LearningExperience<?>>()
                .authenticate(authentication)
                .authorize(studentAuthorization)
                .handle((userId) -> createSimilarLearningExperienceCommandHandler.handle(
                        command.studentUserId(userId)
                ));
    }
}
