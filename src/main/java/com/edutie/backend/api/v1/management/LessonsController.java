package com.edutie.backend.api.v1.management;

import com.edutie.backend.api.common.GenericRequestHandler;
import com.edutie.backend.application.management.lesson.CreateLessonCommandHandler;
import com.edutie.backend.application.management.lesson.CreatedLessonsQueryHandler;
import com.edutie.backend.application.management.lesson.ModifyLessonCommandHandler;
import com.edutie.backend.application.management.lesson.RemoveLessonCommandHandler;
import com.edutie.backend.application.management.lesson.commands.CreateLessonCommand;
import com.edutie.backend.application.management.lesson.commands.ModifyLessonCommand;
import com.edutie.backend.application.management.lesson.commands.RemoveLessonCommand;
import com.edutie.backend.application.management.lesson.queries.CreatedLessonsQuery;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.infrastucture.authentication.ports.AuthenticationPlaceholder;
import com.edutie.backend.infrastucture.authorization.educator.EducatorAuthorization;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.JsonWebToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import validation.Result;
import validation.WrapperResult;

@RestController
@RequestMapping("/api/v1/management/lessons")
@RequiredArgsConstructor
public class LessonsController {
    private final AuthenticationPlaceholder authentication;
    private final CreateLessonCommandHandler createLessonCommandHandler;
    private final ModifyLessonCommandHandler modifyLessonCommandHandler;
    private final RemoveLessonCommandHandler removeLessonCommandHandler;
    private final CreatedLessonsQueryHandler createdLessonsQueryHandler;
    private final EducatorAuthorization educatorAuthorization;

    @GetMapping("/created")
    public ResponseEntity<?> getCreatedLessons() {
        JsonWebToken jwt = new JsonWebToken();
        UserId actionUserId = authentication.authenticateUser(jwt);
        return new GenericRequestHandler<WrapperResult<?>, EducatorAuthorization>()
                .authorize(actionUserId, educatorAuthorization)
                .handle(() -> createdLessonsQueryHandler.handle(new CreatedLessonsQuery(actionUserId)));
    }

    @PostMapping
    public ResponseEntity<?> createLesson(@RequestBody CreateLessonCommand command) {
        JsonWebToken jwt = new JsonWebToken();
        UserId actionUserId = authentication.authenticateUser(jwt);
        return new GenericRequestHandler<WrapperResult<?>, EducatorAuthorization>()
                .authorize(actionUserId, educatorAuthorization)
                .handle(() -> createLessonCommandHandler.handle(command.educatorUserId(actionUserId)));
    }

    @PatchMapping
    public ResponseEntity<?> modifyLesson(@RequestBody ModifyLessonCommand command) {
        JsonWebToken jwt = new JsonWebToken();
        UserId actionUserId = authentication.authenticateUser(jwt);
        return new GenericRequestHandler<Result, EducatorAuthorization>()
                .authorize(actionUserId, educatorAuthorization)
                .handle(() -> modifyLessonCommandHandler.handle(command.educatorUserId(actionUserId)));
    }

    @DeleteMapping
    public ResponseEntity<?> removeLesson(@RequestBody RemoveLessonCommand command) {
        JsonWebToken jwt = new JsonWebToken();
        UserId actionUserId = authentication.authenticateUser(jwt);
        return new GenericRequestHandler<Result, EducatorAuthorization>()
                .authorize(actionUserId, educatorAuthorization)
                .handle(() -> removeLessonCommandHandler.handle(command.educatorUserId(actionUserId)));
    }
}
