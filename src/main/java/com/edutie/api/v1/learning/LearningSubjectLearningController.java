
package com.edutie.api.v1.learning;

import com.edutie.api.common.ApiResult;
import com.edutie.api.common.GenericRequestHandler;
import com.edutie.application.learning.learningsubject.GetCreatedEligibleLearningSubjectsQueryHandler;
import com.edutie.application.learning.learningsubject.GetLearningSubjectLearningViewByIdQueryHandler;
import com.edutie.application.learning.learningsubject.query.GetCreatedEligibleLearningSubjectsQuery;
import com.edutie.application.learning.learningsubject.query.GetLearningSubjectStudentViewByIdQuery;
import com.edutie.application.learning.learningsubject.view.LearningSubjectLearningView;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.identities.LearningSubjectId;
import com.edutie.infrastructure.authorization.educator.EducatorAuthorization;
import com.edutie.infrastructure.authorization.student.StudentAuthorization;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/learning-subject")
@RequiredArgsConstructor
@Tag(name = "Learning Subject Learning Controller", description = "Provides operations regarding learning subjects in the learning context")
public class LearningSubjectLearningController {
    private final StudentAuthorization studentAuthorization;
    private final EducatorAuthorization educatorAuthorization;
    private final GetLearningSubjectLearningViewByIdQueryHandler getLearningSubjectLearningViewByIdQueryHandler;
    private final GetCreatedEligibleLearningSubjectsQueryHandler getCreatedEligibleLearningSubjectsQueryHandler;

    @GetMapping("/{learningSubjectId}/learning")
    @Operation(description = """
            Retrieve learning subject with student's learning view by id.
            """)
    public ResponseEntity<ApiResult<LearningSubjectLearningView>> getLearningSubjectById(Authentication authentication,
                                                                                         @PathVariable LearningSubjectId learningSubjectId) {
        return new GenericRequestHandler<LearningSubjectLearningView>()
                .authenticate(authentication)
                .authorize(studentAuthorization)
                .handle((userId) -> getLearningSubjectLearningViewByIdQueryHandler.handle(
                        new GetLearningSubjectStudentViewByIdQuery()
                                .studentUserId(userId)
                                .learningSubjectId(learningSubjectId)
                ));
    }

    @GetMapping("/eligible")
    @Operation(description = """
            Retrieve eligible learning subjects for the current user. This endpoint authorizes educator profile.
            """)
    public ResponseEntity<ApiResult<List<LearningSubject>>> getEligibleCreatedLearningSubjects(Authentication authentication) {
        return new GenericRequestHandler<List<LearningSubject>>()
                .authenticate(authentication)
                .authorize(educatorAuthorization)
                .handle((userId) -> getCreatedEligibleLearningSubjectsQueryHandler.handle(
                    new GetCreatedEligibleLearningSubjectsQuery()
                            .studentUserId(userId)
                ));
    }

}
