package com.edutie.backend.api.v1.learning;

import com.edutie.backend.api.common.ApiResult;
import com.edutie.backend.api.common.GenericRequestHandler;
import com.edutie.backend.application.learning.ancillaries.RandomFactQueryHandler;
import com.edutie.backend.application.learning.ancillaries.queries.RandomFactQuery;
import com.edutie.backend.application.learning.ancillaries.viewmodels.RandomFact;
import com.edutie.backend.infrastructure.authorization.student.StudentAuthorization;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/learning/ancillaries")
@RequiredArgsConstructor
@Tag(name = "Ancillaries Controller", description = "Provides additional, ancillary operations for the learning process")
public class AncillariesController {
    private final StudentAuthorization studentAuthorization;
    private final RandomFactQueryHandler randomFactQueryHandler;

    @GetMapping("/random-fact")
    @Operation(description = "Retrieves a random fact")
    public ResponseEntity<ApiResult<RandomFact>> getRandomFact(Authentication authentication) {
        return new GenericRequestHandler<RandomFact>()
                .authenticate(authentication)
                .authorize(studentAuthorization)
                .handle((userId) -> randomFactQueryHandler.handle(
                        new RandomFactQuery().studentUserId(userId)
                ));
    }
}
