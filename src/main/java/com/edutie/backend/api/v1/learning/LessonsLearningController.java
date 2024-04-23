package com.edutie.backend.api.v1.learning;

import com.edutie.backend.api.common.GenericRequestHandler;
import com.edutie.backend.application.learning.lesson.LessonsForStudentFromCourseQueryHandler;
import com.edutie.backend.application.learning.lesson.queries.LessonsForStudentFromCourseQuery;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import com.edutie.backend.api.v1.authentication.AuthenticationPlaceholder;
import com.edutie.backend.infrastucture.authorization.student.StudentAuthorization;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.JsonWebToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import validation.WrapperResult;

@RestController
@RequestMapping("api/v1/learning/lessons")
@RequiredArgsConstructor
public class LessonsLearningController {
    private final AuthenticationPlaceholder authentication;
    private final StudentAuthorization studentAuthorization;
    private final LessonsForStudentFromCourseQueryHandler lessonsForStudentFromCourseQueryHandler;

    @GetMapping()
    public ResponseEntity<?> getLessonsForStudentFromCourse(@RequestParam CourseId courseId) {
        UserId actionUserId = authentication.authenticateUser(new JsonWebToken()); //TODO: middleware
        return new GenericRequestHandler<WrapperResult<?>, StudentAuthorization>()
                .authorize(actionUserId, studentAuthorization)
                .handle(() -> lessonsForStudentFromCourseQueryHandler.handle(new LessonsForStudentFromCourseQuery(courseId, actionUserId)));
    }
}
