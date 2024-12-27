package com.edutie.backend.api.v1.learning;

import com.edutie.backend.api.common.ApiResult;
import com.edutie.backend.api.common.GenericRequestHandler;
import com.edutie.backend.application.learning.learningresult.AssessSolutionCommandHandler;
import com.edutie.backend.application.learning.learningresult.GetLearningResultByIdQueryHandler;
import com.edutie.backend.application.learning.learningresult.GetSolutionSubmissionByIdQueryHandler;
import com.edutie.backend.application.learning.learningresult.commands.AssessSolutionCommand;
import com.edutie.backend.application.learning.learningresult.queries.GetLearningResultByIdQuery;
import com.edutie.backend.application.learning.learningresult.queries.GetLearningResultsSolutionSubmissionQuery;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.identities.LearningResultId;
import com.edutie.backend.domain.personalization.solutionsubmission.SolutionSubmission;
import com.edutie.backend.infrastructure.authorization.student.StudentAuthorization;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/learning/results")
@RequiredArgsConstructor
@Tag(name = "Learning Result Controller", description = "Provides operations regarding learning results in the learning context")
public class LearningResultController {
    private final StudentAuthorization studentAuthorization;
    private final GetLearningResultByIdQueryHandler getLearningResultByIdQueryHandler;
    private final AssessSolutionCommandHandler assessSolutionCommandHandler;
    private final GetSolutionSubmissionByIdQueryHandler getSolutionSubmissionByIdQueryHandler;

    @GetMapping("/{learningResultId}")
    @Operation(description = "Retrieves a learning result by its identifier")
    public ResponseEntity<ApiResult<LearningResult>> getLearningResultById(Authentication authentication, @PathVariable LearningResultId learningResultId) {
        return new GenericRequestHandler<LearningResult>()
                .authenticate(authentication)
                .authorize(studentAuthorization)
                .handle((userId) -> getLearningResultByIdQueryHandler.handle(
                        new GetLearningResultByIdQuery().learningResultId(learningResultId).studentUserId(userId))
                );
    }

    @GetMapping("/{learningResultId}/solution-submission")
    @Operation(description = "Retrieves a learning result's solution submission by learning result's identifier")
    public ResponseEntity<ApiResult<SolutionSubmission>> getLearningResultsSolutionSubmission(Authentication authentication, @PathVariable LearningResultId learningResultId) {
        return new GenericRequestHandler<SolutionSubmission>()
                .authenticate(authentication)
                .authorize(studentAuthorization)
                .handle((userId) -> getSolutionSubmissionByIdQueryHandler.handle(
                        new GetLearningResultsSolutionSubmissionQuery().studentUserId(userId).learningResultId(learningResultId)
                ));
    }

    @PostMapping("/create-from-solution")
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
