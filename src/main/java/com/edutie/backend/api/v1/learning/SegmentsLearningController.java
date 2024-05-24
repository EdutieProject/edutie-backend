package com.edutie.backend.api.v1.learning;

import com.edutie.backend.api.common.GenericRequestHandler;
import com.edutie.backend.application.learning.segment.SegmentsForStudentFromLessonQueryHandler;
import com.edutie.backend.application.learning.segment.queries.SegmentsForStudentFromLessonQuery;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import com.edutie.backend.api.v1.authentication.AuthenticationPlaceholder;
import com.edutie.backend.domain.studyprogram.segment.identities.SegmentId;
import com.edutie.backend.infrastucture.authorization.student.StudentAuthorization;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.JsonWebToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import validation.WrapperResult;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/learning/segments")
@RequiredArgsConstructor
public class SegmentsLearningController {
    private final AuthenticationPlaceholder authentication;
    private final StudentAuthorization studentAuthorization;
    private final SegmentsForStudentFromLessonQueryHandler segmentsForStudentFromLessonQueryHandler;

    @GetMapping
    public ResponseEntity<?> getSegmentsForStudentFromLesson(@RequestParam String lessonId) {
        UserId actionUserId = authentication.authenticateUser(new JsonWebToken()); //TODO: middleware ?
        return new GenericRequestHandler<WrapperResult<?>, StudentAuthorization>()
                .authorize(actionUserId, studentAuthorization)
                .handle(() -> segmentsForStudentFromLessonQueryHandler.handle(new SegmentsForStudentFromLessonQuery(actionUserId, new LessonId(UUID.fromString(lessonId)))));
    }
}
