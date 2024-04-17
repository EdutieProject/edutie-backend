package com.edutie.backend.api.v1.learning;

import com.edutie.backend.application.learning.lesson.LessonsForStudentFromCourseQueryHandler;
import com.edutie.backend.application.learning.lesson.queries.LessonsForStudentFromCourseQuery;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import com.edutie.backend.infrastucture.authentication.ports.AuthenticationPlaceholder;
import com.edutie.backend.infrastucture.authorization.student.StudentAuthorization;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.JsonWebToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import validation.Result;
import validation.WrapperResult;

@RestController
@RequestMapping("api/v1/learning/lessons")
@RequiredArgsConstructor
public class LessonsController {
    private final AuthenticationPlaceholder authentication;
    private final StudentAuthorization studentAuthorization;
    private final LessonsForStudentFromCourseQueryHandler lessonsForStudentFromCourseQueryHandler;

    @GetMapping()
    public ResponseEntity<?> getLessonsForStudentFromCourse(@RequestParam CourseId courseId) {
        UserId actionUserId = authentication.authenticateUser(new JsonWebToken()); //TODO: middleware
        Result authorizationResult = studentAuthorization.authorize(actionUserId);
        if (authorizationResult.isFailure())
            return ResponseEntity.status(403).body(authorizationResult);
        WrapperResult<?> result = lessonsForStudentFromCourseQueryHandler.handle(new LessonsForStudentFromCourseQuery(courseId, actionUserId));
        return result.isSuccess() ?
                ResponseEntity.ok(result.getValue())
                : ResponseEntity.status(400).body(result.getValue());
    }
}
