package com.edutie.backend.api.v1.learning;

import com.edutie.backend.api.common.ApiResult;
import com.edutie.backend.api.common.GenericRequestHandler;
import com.edutie.backend.application.learning.learningresource.AssessSolutionCommandHandler;
import com.edutie.backend.application.learning.learningresource.GetLearningResourceByIdQueryHandler;
import com.edutie.backend.application.learning.learningresource.commands.AssessSolutionCommand;
import com.edutie.backend.application.learning.learningresource.CreateLearningResourceCommandHandler;
import com.edutie.backend.application.learning.learningresource.commands.CreateLearningResourceCommand;
import com.edutie.backend.application.learning.learningresource.queries.GetLearningResourceByIdQuery;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.identities.LearningResourceId;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.infrastucture.authorization.student.StudentAuthorization;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/learning/learning-resource")
@RequiredArgsConstructor
@Tag(name = "Learning Resource Controller", description = "Provides operations regarding learning resources in the learning context")
public class LearningResourceController {
    private final StudentAuthorization studentAuthorization;
    private final CreateLearningResourceCommandHandler createLearningResourceCommandHandler;
    private final GetLearningResourceByIdQueryHandler getLearningResourceByIdQueryHandler;
    private final AssessSolutionCommandHandler assessSolutionCommandHandler;

    @GetMapping
    public ResponseEntity<ApiResult<LearningResource>> getLearningResourceById(Authentication authentication,
                                                                               @RequestParam LearningResourceId learningResourceId) {
        return new GenericRequestHandler<LearningResource>()
                .authenticate(authentication)
                .authorize(studentAuthorization)
                .handle((userId) -> getLearningResourceByIdQueryHandler.handle(
                        new GetLearningResourceByIdQuery().learningResourceId(learningResourceId).studentUserId(userId)
                ));
    }

    @PostMapping
    public ResponseEntity<ApiResult<LearningResource>> createLearningResource(Authentication authentication,
                                                                              @RequestBody CreateLearningResourceCommand command) {
        return new GenericRequestHandler<LearningResource>()
                .authenticate(authentication)
                .authorize(studentAuthorization)
                .handle((userId) -> createLearningResourceCommandHandler.handle(command.studentUserId(userId)));
    }

    @PostMapping("/assess-solution")
    public ResponseEntity<ApiResult<LearningResult>> assessSolution(Authentication authentication,
                                                                    @RequestBody AssessSolutionCommand command) {
        return new GenericRequestHandler<LearningResult>()
                .authenticate(authentication)
                .authorize(studentAuthorization)
                .handle((userId) -> assessSolutionCommandHandler.handle(command.studentUserId(userId)));
    }
}
