package com.edutie.backend.api.v1.management;

import com.edutie.backend.api.common.GenericRequestHandler;
import com.edutie.backend.application.management.course.CreateCourseCommandHandler;
import com.edutie.backend.application.management.course.CreatedCoursesQueryHandler;
import com.edutie.backend.application.management.course.ModifyCourseCommandHandler;
import com.edutie.backend.application.management.course.RemoveCourseCommandHandler;
import com.edutie.backend.application.management.course.commands.CreateCourseCommand;
import com.edutie.backend.application.management.course.commands.ModifyCourseCommand;
import com.edutie.backend.application.management.course.commands.RemoveCourseCommand;
import com.edutie.backend.application.management.course.queries.CreatedCoursesQuery;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.api.v1.authentication.AuthenticationPlaceholder;
import com.edutie.backend.infrastucture.authorization.educator.EducatorAuthorization;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.JsonWebToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import validation.Result;
import validation.WrapperResult;

@RestController
@RequestMapping("api/v1/management/courses")
@RequiredArgsConstructor
@Tag(name = "Courses Management Controller", description = "Provides operations regarding courses in the management context")
public class CoursesManagementController {
    private final AuthenticationPlaceholder authentication;
    private final CreateCourseCommandHandler createCourseCommandHandler;
    private final ModifyCourseCommandHandler modifyCourseCommandHandler;
    private final RemoveCourseCommandHandler removeCourseCommandHandler;
    private final CreatedCoursesQueryHandler createdCoursesQueryHandler;
    private final EducatorAuthorization educatorAuthorization;

    @GetMapping
    public ResponseEntity<?> getCreatedCourses() {
        JsonWebToken jwt = new JsonWebToken();
        UserId actionUserId = authentication.authenticateUser(jwt);
        return new GenericRequestHandler<WrapperResult<?>, EducatorAuthorization>()
                .authorize(actionUserId, educatorAuthorization)
                .handle(() -> createdCoursesQueryHandler.handle(new CreatedCoursesQuery(actionUserId)));
    }

    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody CreateCourseCommand command) {
        JsonWebToken jwt = new JsonWebToken();
        UserId actionUserId = authentication.authenticateUser(jwt);
        return new GenericRequestHandler<WrapperResult<?>, EducatorAuthorization>()
                .authorize(actionUserId, educatorAuthorization)
                .handle(() -> createCourseCommandHandler.handle(command.educatorUserId(actionUserId)));
    }

    @PatchMapping
    public ResponseEntity<?> modifyCourse(@RequestBody ModifyCourseCommand command) {
        UserId actionUserId = authentication.authenticateUser(new JsonWebToken());
        return new GenericRequestHandler<Result, EducatorAuthorization>()
                .authorize(actionUserId, educatorAuthorization)
                .handle(() -> modifyCourseCommandHandler.handle(command.educatorUserId(actionUserId)));
    }

    @DeleteMapping
    public ResponseEntity<?> removeCourse(@RequestBody RemoveCourseCommand command) {
        UserId actionUserId = authentication.authenticateUser(new JsonWebToken());
        return new GenericRequestHandler<Result, EducatorAuthorization>()
                .authorize(actionUserId, educatorAuthorization)
                .handle(() -> removeCourseCommandHandler.handle(command.educatorUserId(actionUserId)));
    }

}
