package com.edutie.api.v1.management;

import com.edutie.api.common.ApiResult;
import com.edutie.api.common.GenericRequestHandler;
import com.edutie.application.management.knowledgesubject.SearchKnowledgeSubjectsQueryHandler;
import com.edutie.application.management.knowledgesubject.query.SearchKnowledgeSubjectsQuery;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.infrastructure.authorization.educator.EducatorAuthorization;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/knowledge-subject")
@RequiredArgsConstructor
@Tag(name = "Knowledge Subject Controller", description = "Provides operations regarding knowledge subjects in the management context")
public class KnowledgeSubjectController {
    private final EducatorAuthorization educatorAuthorization;
    private final SearchKnowledgeSubjectsQueryHandler searchKnowledgeSubjectsQueryHandler;

    @GetMapping("/search")
    @Operation(description = """
            Searches knowledge subjects by provided parameters
            """)
    public ResponseEntity<ApiResult<LearningSubject>> searchKnowledgeSubjects(Authentication authentication,
                                                                              @RequestParam String searchName) {
        return new GenericRequestHandler<LearningSubject>()
                .authenticate(authentication)
                .authorize(educatorAuthorization)
                .handle((userId) -> searchKnowledgeSubjectsQueryHandler.handle(
                        new SearchKnowledgeSubjectsQuery()
                                .educatorUserId(userId)
                                .knowledgeSubjectSearchName(searchName)
                ));
    }

}
