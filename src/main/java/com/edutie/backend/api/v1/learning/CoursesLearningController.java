package com.edutie.backend.api.v1.learning;

import com.edutie.backend.api.common.ApiResult;
import com.edutie.backend.api.common.GenericRequestHandler;
import com.edutie.backend.api.v1.authentication.AuthenticationPlaceholder;
import com.edutie.backend.application.learning.course.CoursesByScienceQueryHandler;
import com.edutie.backend.application.learning.course.CoursesByStudentProgressQueryHandler;
import com.edutie.backend.application.learning.course.queries.CoursesByScienceQuery;
import com.edutie.backend.application.learning.course.queries.CoursesByStudentProgressQuery;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import com.edutie.backend.infrastucture.authorization.student.StudentAuthorization;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.JsonWebToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/learning/courses")
@RequiredArgsConstructor
public class CoursesLearningController {
    private final AuthenticationPlaceholder authentication;
    private final StudentAuthorization studentAuthorization;
    private final CoursesByScienceQueryHandler coursesByScienceQueryHandler;
    private final CoursesByStudentProgressQueryHandler coursesByStudentProgressQueryHandler;


    @GetMapping
    public ResponseEntity<ApiResult<List<Course>>> getCoursesByScience(@RequestParam String scienceId) {
        UserId actionUserId = authentication.authenticateUser(new JsonWebToken()); // TODO: middleware?
        return new GenericRequestHandler<List<Course>, StudentAuthorization>()
                .authorize(actionUserId, studentAuthorization)
                .handle(() -> coursesByScienceQueryHandler.handle(new CoursesByScienceQuery(new ScienceId(UUID.fromString(scienceId)))));
    }

    @GetMapping("/progressed")
    public ResponseEntity<ApiResult<List<Course>>> getCoursesByStudentProgress() {
        UserId actionUserId = authentication.authenticateUser(new JsonWebToken()); // TODO: middleware?
        return new GenericRequestHandler<List<Course>, StudentAuthorization>()
                .authorize(actionUserId, studentAuthorization)
                .handle(() -> coursesByStudentProgressQueryHandler.handle(new CoursesByStudentProgressQuery(actionUserId)));
    }
}
