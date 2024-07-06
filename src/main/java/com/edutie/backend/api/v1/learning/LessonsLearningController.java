package com.edutie.backend.api.v1.learning;

import com.edutie.backend.api.common.ApiResult;
import com.edutie.backend.api.common.GenericRequestHandler;
import com.edutie.backend.api.authentication.AuthenticationPlaceholder;
import com.edutie.backend.application.learning.lesson.LessonsForStudentFromCourseQueryHandler;
import com.edutie.backend.application.learning.lesson.queries.LessonsForStudentFromCourseQuery;
import com.edutie.backend.application.learning.lesson.viewmodels.LessonView;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import com.edutie.backend.infrastucture.authorization.student.StudentAuthorization;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/learning/lessons")
@RequiredArgsConstructor
@Tag(name = "Lessons Learning Controller", description = "Provides operations regarding lessons in the learning context")
public class LessonsLearningController {
    private final AuthenticationPlaceholder authentication;
    private final StudentAuthorization studentAuthorization;
    private final LessonsForStudentFromCourseQueryHandler lessonsForStudentFromCourseQueryHandler;

    @GetMapping
    public ResponseEntity<ApiResult<List<LessonView>>> getLessonsForStudentFromCourse(Authentication auth, @RequestParam CourseId courseId) {
        UserId actionUserId = authentication.authenticateUser(auth);
        return new GenericRequestHandler<List<LessonView>, StudentAuthorization>()
                .authorize(actionUserId, studentAuthorization)
                .handle(() -> lessonsForStudentFromCourseQueryHandler.handle(
                        new LessonsForStudentFromCourseQuery().studentUserId(actionUserId).courseId(courseId)
                ));
    }
}
