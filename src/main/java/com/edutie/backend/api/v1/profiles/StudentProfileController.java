package com.edutie.backend.api.v1.profiles;

import com.edutie.backend.api.common.ApiResult;
import com.edutie.backend.api.common.GenericRequestHandler;
import com.edutie.backend.application.profiles.student.GetLatestLearningResultsQueryHandler;
import com.edutie.backend.application.profiles.student.queries.GetLatestLearningResultsQuery;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.infrastucture.authorization.student.StudentAuthorization;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
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

    @GetMapping("/learning-results/latest")
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
                                .maxDate(LocalDateTime.parse(maxDate, DateTimeFormatter.ISO_DATE_TIME))
                )));
    }
}
