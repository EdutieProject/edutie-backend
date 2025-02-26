package com.edutie.api.v1.profiles;

import com.edutie.api.common.ApiResult;
import com.edutie.api.common.GenericRequestHandler;
import com.edutie.application.profiles.student.GetLatestLearningResultsQueryHandler;
import com.edutie.application.profiles.student.queries.GetLatestLearningResultsQuery;
import com.edutie.domain.core.learning.learningresult.LearningResult;
import com.edutie.infrastructure.authorization.student.StudentAuthorization;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/v1/profiles/student")
@RequiredArgsConstructor
@Tag(name = "Student Profile Controller", description = "Provides a range of operations regarding student profile")
public class StudentProfileController {
    private final StudentAuthorization studentAuthorization;
    private final GetLatestLearningResultsQueryHandler getLatestLearningResultsQueryHandler;

    @GetMapping("/learning-results/retrieve-latest")
    @Operation(description = "Retrieves latest learning results for given student")
    public ResponseEntity<ApiResult<List<LearningResult>>> getLatestLearningResults(Authentication authentication,
                                                                                    @RequestParam(required = false) Integer amount,
                                                                                    String maxDate) {
        return new GenericRequestHandler<List<LearningResult>>()
                .authenticate(authentication)
                .authorize(studentAuthorization)
                .handle((userId -> getLatestLearningResultsQueryHandler.handle(
                        new GetLatestLearningResultsQuery()
                                .studentUserId(userId)
                                .amount(amount)
                                .maxDate(maxDate != null ? LocalDateTime.parse(maxDate, DateTimeFormatter.ISO_DATE_TIME) : null)
                )));
    }
}
