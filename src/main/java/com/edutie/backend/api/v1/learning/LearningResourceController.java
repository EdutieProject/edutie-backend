package com.edutie.backend.api.v1.learning;

import com.edutie.backend.api.common.ApiResult;
import com.edutie.backend.api.common.GenericRequestHandler;
import com.edutie.backend.application.learning.learningresource.CreateLearningResourceCommandHandler;
import com.edutie.backend.application.learning.learningresource.commands.CreateLearningResourceCommand;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.infrastucture.authorization.student.StudentAuthorization;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/learning/learning-resource")
@RequiredArgsConstructor
@Tag(name = "Learning Resource Controller", description = "Provides operations regarding learning resources in the learning context")
public class LearningResourceController {
    private final StudentAuthorization studentAuthorization;
    private final CreateLearningResourceCommandHandler createLearningResourceCommandHandler;

    @PostMapping
    public ResponseEntity<ApiResult<LearningResource>> createLearningResource(Authentication authentication,
                                                                              @RequestBody CreateLearningResourceCommand command) {
        return new GenericRequestHandler<LearningResource>()
                .authenticate(authentication)
                .authorize(studentAuthorization)
                .handle((userId)->createLearningResourceCommandHandler.handle(command.studentUserId(userId)));
    }
}
