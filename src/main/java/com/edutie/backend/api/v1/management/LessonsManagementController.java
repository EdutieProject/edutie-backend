package com.edutie.backend.api.v1.management;

import com.edutie.backend.api.common.ApiResult;
import com.edutie.backend.api.common.GenericRequestHandler;
import com.edutie.backend.api.authentication.AuthenticationPlaceholder;
import com.edutie.backend.application.management.lesson.CreateLessonCommandHandler;
import com.edutie.backend.application.management.lesson.CreatedLessonsQueryHandler;
import com.edutie.backend.application.management.lesson.ModifyLessonCommandHandler;
import com.edutie.backend.application.management.lesson.RemoveLessonCommandHandler;
import com.edutie.backend.application.management.lesson.commands.CreateLessonCommand;
import com.edutie.backend.application.management.lesson.commands.ModifyLessonCommand;
import com.edutie.backend.application.management.lesson.commands.RemoveLessonCommand;
import com.edutie.backend.application.management.lesson.queries.CreatedLessonsQuery;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.infrastucture.authorization.educator.EducatorAuthorization;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import validation.Result;

import java.util.List;

@RestController
@RequestMapping("/api/v1/management/lessons")
@RequiredArgsConstructor
@Tag(name = "Lessons Management Controller", description = "Provides operations regarding lessons in the management context")
public class LessonsManagementController {
    private final AuthenticationPlaceholder authentication;
    private final CreateLessonCommandHandler createLessonCommandHandler;
    private final ModifyLessonCommandHandler modifyLessonCommandHandler;
    private final RemoveLessonCommandHandler removeLessonCommandHandler;
    private final CreatedLessonsQueryHandler createdLessonsQueryHandler;
    private final EducatorAuthorization educatorAuthorization;

    @GetMapping
    public ResponseEntity<ApiResult<List<Lesson>>> getCreatedLessons(Authentication auth) {
        UserId actionUserId = authentication.authenticateUser(auth);
        return new GenericRequestHandler<List<Lesson>, EducatorAuthorization>()
                .authorize(actionUserId, educatorAuthorization)
                .handle(() -> createdLessonsQueryHandler.handle(new CreatedLessonsQuery().educatorUserId(actionUserId)));
    }

    @PostMapping
    public ResponseEntity<ApiResult<Lesson>> createLesson(Authentication auth, @RequestBody CreateLessonCommand command) {
        UserId actionUserId = authentication.authenticateUser(auth);
        return new GenericRequestHandler<Lesson, EducatorAuthorization>()
                .authorize(actionUserId, educatorAuthorization)
                .handle(() -> createLessonCommandHandler.handle(command.educatorUserId(actionUserId)));
    }

    @PatchMapping
    public ResponseEntity<ApiResult<Result>> modifyLesson(Authentication auth, @RequestBody ModifyLessonCommand command) {
        UserId actionUserId = authentication.authenticateUser(auth);
        return new GenericRequestHandler<Result, EducatorAuthorization>()
                .authorize(actionUserId, educatorAuthorization)
                .handle(() -> modifyLessonCommandHandler.handle(command.educatorUserId(actionUserId)));
    }

    @DeleteMapping
    public ResponseEntity<ApiResult<Result>> removeLesson(Authentication auth, @RequestBody RemoveLessonCommand command) {
        UserId actionUserId = authentication.authenticateUser(auth);
        return new GenericRequestHandler<Result, EducatorAuthorization>()
                .authorize(actionUserId, educatorAuthorization)
                .handle(() -> removeLessonCommandHandler.handle(command.educatorUserId(actionUserId)));
    }
}
