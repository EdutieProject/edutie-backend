package com.edutie.backend.api.v1.management;

import com.edutie.backend.api.common.ApiResult;
import com.edutie.backend.api.common.GenericRequestHandler;
import com.edutie.backend.api.authentication.AuthenticationPlaceholder;
import com.edutie.backend.application.management.course.CreateCourseCommandHandler;
import com.edutie.backend.application.management.course.CreatedCoursesQueryHandler;
import com.edutie.backend.application.management.course.ModifyCourseCommandHandler;
import com.edutie.backend.application.management.course.RemoveCourseCommandHandler;
import com.edutie.backend.application.management.course.commands.CreateCourseCommand;
import com.edutie.backend.application.management.course.commands.ModifyCourseCommand;
import com.edutie.backend.application.management.course.commands.RemoveCourseCommand;
import com.edutie.backend.application.management.course.queries.CreatedCoursesQuery;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.infrastucture.authorization.educator.EducatorAuthorization;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import validation.Result;

import java.util.List;

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
    public ResponseEntity<ApiResult<List<Course>>> getCreatedCourses(Authentication auth) {
        UserId actionUserId = authentication.authenticateUser(auth);
        return new GenericRequestHandler<List<Course>, EducatorAuthorization>()
                .authorize(actionUserId, educatorAuthorization)
                .handle(() -> createdCoursesQueryHandler.handle(new CreatedCoursesQuery().educatorUserId(actionUserId)));
    }

    @PostMapping
    public ResponseEntity<ApiResult<Course>> createCourse(Authentication auth, @RequestBody CreateCourseCommand command) {
        UserId actionUserId = authentication.authenticateUser(auth);
        return new GenericRequestHandler<Course, EducatorAuthorization>()
                .authorize(actionUserId, educatorAuthorization)
                .handle(() -> createCourseCommandHandler.handle(command.educatorUserId(actionUserId)));
    }

    @PatchMapping
    public ResponseEntity<ApiResult<Result>> modifyCourse(Authentication auth, @RequestBody ModifyCourseCommand command) {
        UserId actionUserId = authentication.authenticateUser(auth);
        return new GenericRequestHandler<Result, EducatorAuthorization>()
                .authorize(actionUserId, educatorAuthorization)
                .handle(() -> modifyCourseCommandHandler.handle(command.educatorUserId(actionUserId)));
    }

    @DeleteMapping
    public ResponseEntity<ApiResult<Result>> removeCourse(Authentication auth, @RequestBody RemoveCourseCommand command) {
        UserId actionUserId = authentication.authenticateUser(auth);
        return new GenericRequestHandler<Result, EducatorAuthorization>()
                .authorize(actionUserId, educatorAuthorization)
                .handle(() -> removeCourseCommandHandler.handle(command.educatorUserId(actionUserId)));
    }

}
