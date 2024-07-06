package com.edutie.backend.api.v1.learning;

import com.edutie.backend.api.common.ApiResult;
import com.edutie.backend.api.common.GenericRequestHandler;
import com.edutie.backend.api.authentication.AuthenticationPlaceholder;
import com.edutie.backend.application.learning.segment.SegmentsForStudentFromLessonQueryHandler;
import com.edutie.backend.application.learning.segment.queries.SegmentsForStudentFromLessonQuery;
import com.edutie.backend.application.learning.segment.viewmodels.SegmentView;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
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
@RequestMapping("api/v1/learning/segments")
@RequiredArgsConstructor
@Tag(name = "Segments Learning Controller", description = "Provides operations regarding segments in the learning context")
public class SegmentsLearningController {
    private final AuthenticationPlaceholder authentication;
    private final StudentAuthorization studentAuthorization;
    private final SegmentsForStudentFromLessonQueryHandler segmentsForStudentFromLessonQueryHandler;

    @GetMapping
    public ResponseEntity<ApiResult<List<SegmentView>>> getSegmentsForStudentFromLesson(Authentication auth, @RequestParam LessonId lessonId) {
        UserId actionUserId = authentication.authenticateUser(auth);
        return new GenericRequestHandler<List<SegmentView>, StudentAuthorization>()
                .authorize(actionUserId, studentAuthorization)
                .handle(() -> segmentsForStudentFromLessonQueryHandler.handle(
                        new SegmentsForStudentFromLessonQuery().studentUserId(actionUserId).lessonId(lessonId)
                ));
    }
}
