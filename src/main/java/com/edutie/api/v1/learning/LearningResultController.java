package com.edutie.api.v1.learning;

import com.edutie.api.common.ApiResult;
import com.edutie.api.common.GenericRequestHandler;
import com.edutie.application.learning.learningresult.CreateLearningResultCommandHandler;
import com.edutie.application.learning.learningresult.GetLearningResultByIdQueryHandler;
import com.edutie.application.learning.learningresult.command.CreateLearningResultCommand;
import com.edutie.application.learning.learningresult.query.GetLearningResultByIdQuery;
import com.edutie.application.learning.learningresult.view.LearningResultView;
import com.edutie.domain.core.learning.learningresult.entities.submission.SimpleProblemActivitySolutionSubmission;
import com.edutie.domain.core.learning.learningresult.identities.LearningResultId;
import com.edutie.infrastructure.authorization.student.StudentAuthorization;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/learning-result")
@RequiredArgsConstructor
@Tag(name = "Learning Result Controller", description = "Provides operations regarding learning results in the learning context")
public class LearningResultController {
    private final StudentAuthorization studentAuthorization;
    private final GetLearningResultByIdQueryHandler getLearningResultByIdQueryHandler;
    //todo: decide whether there exists generic & type-safe solution for this
    private final CreateLearningResultCommandHandler<SimpleProblemActivitySolutionSubmission> createLearningResultCommandHandler;

    @GetMapping("/{learningResultId}")
    @Operation(description = "Retrieves a learning result by its identifier")
    public ResponseEntity<ApiResult<LearningResultView<?>>> getLearningResultById(Authentication authentication, @PathVariable LearningResultId learningResultId) {
        return new GenericRequestHandler<LearningResultView<?>>()
                .authenticate(authentication)
                .authorize(studentAuthorization)
                .handle((userId) -> getLearningResultByIdQueryHandler.handle(
                        new GetLearningResultByIdQuery().learningResultId(learningResultId).studentUserId(userId))
                );
    }

    //todo: consider adding simple problem activity solution details entity
    @PostMapping("/create/simple-problem-activity")
    @Operation(description = "Creates a learning result using a simple problem activity solution submission")
    public ResponseEntity<ApiResult<LearningResultView<SimpleProblemActivitySolutionSubmission>>> createSimpleProblemActivityLearningResult(
            Authentication authentication,
            @RequestBody CreateLearningResultCommand<SimpleProblemActivitySolutionSubmission> command) {
        return new GenericRequestHandler<LearningResultView<SimpleProblemActivitySolutionSubmission>>()
                .authenticate(authentication)
                .authorize(studentAuthorization)
                .handle((userId) -> createLearningResultCommandHandler.handle(command.studentUserId(userId)));
    }
}
