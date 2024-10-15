package com.edutie.backend.api.v1.learning;

import com.edutie.backend.api.common.ApiResult;
import com.edutie.backend.api.common.GenericRequestHandler;
import com.edutie.backend.application.learning.learningresult.GetLearningResultByIdQueryHandler;
import com.edutie.backend.application.learning.learningresult.queries.GetLearningResultByIdQuery;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresult.identities.LearningResultId;
import com.edutie.backend.infrastucture.authorization.student.StudentAuthorization;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/learning/learning-result")
@RequiredArgsConstructor
@Tag(name = "Learning Result Controller", description = "Provides operations regarding learning results in the learning context")
public class LearningResultController {
	private final StudentAuthorization studentAuthorization;
	private final GetLearningResultByIdQueryHandler getLearningResultByIdQueryHandler;

	@GetMapping
	@Operation(description = "Retrieves a learning result by its identifier")
	public ResponseEntity<ApiResult<LearningResource>> getLearningResultById(Authentication authentication, @RequestParam LearningResultId learningResultId) {
		return new GenericRequestHandler<LearningResource>()
				.authenticate(authentication)
				.authorize(studentAuthorization)
				.handle((userId) -> getLearningResultByIdQueryHandler.handle(
						new GetLearningResultByIdQuery().learningResultId(learningResultId).studentUserId(userId))
				);
	}
}
