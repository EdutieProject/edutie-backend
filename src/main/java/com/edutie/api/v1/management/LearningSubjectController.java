package com.edutie.api.v1.management;

import com.edutie.api.common.ApiResult;
import com.edutie.api.common.GenericRequestHandler;
import com.edutie.application.management.learningsubject.*;
import com.edutie.application.management.learningsubject.command.AddLearningSubjectRequirementCommand;
import com.edutie.application.management.learningsubject.command.CreateBlankLearningSubjectCommand;
import com.edutie.application.management.learningsubject.command.RemoveLearningSubjectRequirementCommand;
import com.edutie.application.management.learningsubject.command.SetLearningSubjectKnowledgeSubjectCommand;
import com.edutie.application.management.learningsubject.query.GetLearningSubjectByIdQuery;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.identities.LearningSubjectId;
import com.edutie.infrastructure.authorization.educator.EducatorAuthorization;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/learning-subject")
@RequiredArgsConstructor
@Tag(name = "Learning Subject Controller", description = "Provides operations regarding learning subjects in the management context")
public class LearningSubjectController {
    private final EducatorAuthorization educatorAuthorization;
    private final GetLearningSubjectByIdQueryHandler getLearningSubjectByIdQueryHandler;
    private final CreateBlankLearningSubjectCommandHandler createBlankLearningSubjectCommandHandler;
    private final AddLearningSubjectRequirementCommandHandler addLearningSubjectRequirementCommandHandler;
    private final RemoveLearningSubjectRequirementCommandHandler removeLearningSubjectRequirementCommandHandler;
    private final SetLearningSubjectKnowledgeSubjectCommandHandler setLearningSubjectKnowledgeSubjectCommandHandler;

    @GetMapping("/{learningSubjectId}")
    @Operation(description = """
            Creates a blank learning subject assigning its name.
            """)
    public ResponseEntity<ApiResult<LearningSubject>> createBlankLearningSubject(Authentication authentication,
                                                                                 @PathVariable LearningSubjectId learningSubjectId) {
        return new GenericRequestHandler<LearningSubject>()
                .authenticate(authentication)
                .authorize(educatorAuthorization)
                .handle((userId) -> getLearningSubjectByIdQueryHandler.handle(
                        new GetLearningSubjectByIdQuery()
                                .educatorUserId(userId)
                                .learningSubjectId(learningSubjectId)
                ));
    }

    @PostMapping("/create")
    @Operation(description = """
            Creates a blank learning subject assigning its name.
            """)
    public ResponseEntity<ApiResult<LearningSubject>> createBlankLearningSubject(Authentication authentication,
                                                                                 @RequestBody CreateBlankLearningSubjectCommand command) {
        return new GenericRequestHandler<LearningSubject>()
                .authenticate(authentication)
                .authorize(educatorAuthorization)
                .handle((userId) -> createBlankLearningSubjectCommandHandler.handle(
                        command.educatorUserId(userId)
                ));
    }

    @PostMapping("/add-requirement")
    @Operation(description = """
            Adds a requirement for the learning subject
            """)
    public ResponseEntity<ApiResult<LearningSubject>> addLearningSubjectRequirement(Authentication authentication,
                                                                                    @RequestBody AddLearningSubjectRequirementCommand command) {
        return new GenericRequestHandler<LearningSubject>()
                .authenticate(authentication)
                .authorize(educatorAuthorization)
                .handle((userId) -> addLearningSubjectRequirementCommandHandler.handle(
                        command.educatorUserId(userId)
                ));
    }

    @PostMapping("/remove-requirement")
    @Operation(description = """
            Removes a requirement from the learning subject
            """)
    public ResponseEntity<ApiResult<LearningSubject>> removeLearningSubjectRequirement(Authentication authentication,
                                                                                       @RequestBody RemoveLearningSubjectRequirementCommand command) {
        return new GenericRequestHandler<LearningSubject>()
                .authenticate(authentication)
                .authorize(educatorAuthorization)
                .handle((userId) -> removeLearningSubjectRequirementCommandHandler.handle(
                        command.educatorUserId(userId)
                ));
    }

    @PostMapping("/set-knowledge-subject")
    @Operation(description = """
            Sets learning subject's knowledge subject id
            """)
    public ResponseEntity<ApiResult<LearningSubject>> setLearningSubjectKnowledgeSubjectId(Authentication authentication,
                                                                                           @RequestBody SetLearningSubjectKnowledgeSubjectCommand command) {
        return new GenericRequestHandler<LearningSubject>()
                .authenticate(authentication)
                .authorize(educatorAuthorization)
                .handle((userId) -> setLearningSubjectKnowledgeSubjectCommandHandler.handle(
                        command.educatorUserId(userId)
                ));
    }
}
