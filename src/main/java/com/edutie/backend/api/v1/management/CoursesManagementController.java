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
    private final CreateCourseCommandHandler createCourseCommandHandler;
    private final ModifyCourseCommandHandler modifyCourseCommandHandler;
    private final RemoveCourseCommandHandler removeCourseCommandHandler;
    private final CreatedCoursesQueryHandler createdCoursesQueryHandler;
    private final EducatorAuthorization educatorAuthorization;

    @GetMapping
    public ResponseEntity<ApiResult<List<Course>>> getCreatedCourses(Authentication auth) {
        return new GenericRequestHandler<List<Course>>()
                .authenticate(auth)
                .authorize(educatorAuthorization)
                .handle((userId) -> createdCoursesQueryHandler.handle(new CreatedCoursesQuery().educatorUserId(userId)));
    }

    @PostMapping
    public ResponseEntity<ApiResult<Course>> createCourse(Authentication auth, @RequestBody CreateCourseCommand command) {
        return new GenericRequestHandler<Course>()
                .authenticate(auth)
                .authorize(educatorAuthorization)
                .handle((userId) -> createCourseCommandHandler.handle(command.educatorUserId(userId)));
    }

    @PatchMapping
    public ResponseEntity<ApiResult<Result>> modifyCourse(Authentication auth, @RequestBody ModifyCourseCommand command) {
        return new GenericRequestHandler<Result>()
                .authenticate(auth)
                .authorize(educatorAuthorization)
                .handle((userId) -> modifyCourseCommandHandler.handle(command.educatorUserId(userId)));
    }

    @DeleteMapping
    public ResponseEntity<ApiResult<Result>> removeCourse(Authentication auth, @RequestBody RemoveCourseCommand command) {
        return new GenericRequestHandler<Result>()
                .authenticate(auth)
                .authorize(educatorAuthorization)
                .handle((userId) -> removeCourseCommandHandler.handle(command.educatorUserId(userId)));
    }

}
