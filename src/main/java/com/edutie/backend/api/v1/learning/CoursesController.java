package com.edutie.backend.api.v1.learning;

import com.edutie.backend.application.learning.course.queries.CoursesByScienceQuery;
import com.edutie.backend.application.learning.course.queries.CoursesByStudentProgressQuery;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import com.edutie.backend.infrastucture.authentication.ports.AuthenticationPlaceholder;
import com.edutie.backend.infrastucture.authorization.student.StudentAuthorization;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.JsonWebToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edutie.backend.application.learning.course.*;
import validation.Result;
import validation.WrapperResult;

@RestController
@RequestMapping("api/v1/learning/courses")
@RequiredArgsConstructor
public class CoursesController {
    private final AuthenticationPlaceholder authentication;
    private final StudentAuthorization studentAuthorization;
    private final CoursesByScienceQueryHandler coursesByScienceQueryHandler;
    private final CoursesByStudentProgressQueryHandler coursesByStudentProgressQueryHandler;


    @GetMapping
    public ResponseEntity<?> getCoursesByScience(@RequestParam ScienceId scienceId) {
        authentication.authenticateUser(new JsonWebToken()); // TODO: middleware?
        WrapperResult<?> result = coursesByScienceQueryHandler.handle(new CoursesByScienceQuery(scienceId));
        return result.isSuccess() ?
                ResponseEntity.ok(result.getValue())
                : ResponseEntity.status(400).body(result);
    }

    @GetMapping("/started")
    public ResponseEntity<?> getCoursesByStudentProgress() {
        UserId actionUserId = authentication.authenticateUser(new JsonWebToken()); // TODO: middleware?
        Result authorizationResult = studentAuthorization.authorize(actionUserId);
        if (authorizationResult.isFailure())
            return ResponseEntity.status(403).body(authorizationResult);
        WrapperResult<?> result = coursesByStudentProgressQueryHandler.handle(new CoursesByStudentProgressQuery(actionUserId));
        return result.isSuccess() ?
                ResponseEntity.ok(result.getValue())
                : ResponseEntity.status(400).body(result);
    }
}
