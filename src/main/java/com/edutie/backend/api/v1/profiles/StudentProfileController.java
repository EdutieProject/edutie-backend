package com.edutie.backend.api.v1.profiles;

import com.edutie.backend.api.common.ApiResult;
import com.edutie.backend.api.common.GenericRequestHandler;
import com.edutie.backend.application.profiles.student.GetLatestLearningResultsQueryHandler;
import com.edutie.backend.application.profiles.student.queries.GetLatestLearningResultsQuery;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.infrastucture.authorization.student.StudentAuthorization;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profiles/student")
@RequiredArgsConstructor
public class StudentProfileController {
    private final StudentAuthorization studentAuthorization;
    private final GetLatestLearningResultsQueryHandler getLatestLearningResultsQueryHandler;

    @GetMapping("/learning-results/latest")
    public ResponseEntity<ApiResult<List<LearningResult>>> getLatestLearningResults(Authentication authentication, @RequestParam int amount) {
        return new GenericRequestHandler<List<LearningResult>>()
                .authenticate(authentication)
                .authorize(studentAuthorization)
                .handle((userId -> getLatestLearningResultsQueryHandler.handle(
                        new GetLatestLearningResultsQuery().studentUserId(userId).amount(amount)
                )));
    }
}
