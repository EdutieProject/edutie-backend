package com.edutie.backend.api.v1.learning;

import com.edutie.backend.api.common.ApiResult;
import com.edutie.backend.api.common.GenericRequestHandler;
import com.edutie.backend.application.learning.science.AccessibleSciencesQueryHandler;
import com.edutie.backend.application.learning.science.queries.AccessibleSciencesQuery;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.infrastucture.authorization.student.StudentAuthorization;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/learning/sciences")
@RequiredArgsConstructor
@Tag(name = "Sciences Learning Controller", description = "Provides operations regarding sciences in the learning context")
public class ScienceLearningController {
    private final AccessibleSciencesQueryHandler accessibleSciencesQueryHandler;
    private final StudentAuthorization studentAuthorization;

    @GetMapping
    public ResponseEntity<ApiResult<List<Science>>> getAllSciences(Authentication auth) {
        return new GenericRequestHandler<List<Science>>()
                .authenticate(auth)
                .authorize(studentAuthorization)
                .handle(() -> accessibleSciencesQueryHandler.handle(new AccessibleSciencesQuery()));

    }

}