package com.edutie.backend.api.v1.learning;

import com.edutie.backend.api.common.ApiResult;
import com.edutie.backend.api.common.GenericRequestHandler;
import com.edutie.backend.application.learning.course.CoursesByScienceQueryHandler;
import com.edutie.backend.application.learning.course.CoursesByStudentProgressQueryHandler;
import com.edutie.backend.application.learning.course.queries.CoursesByScienceQuery;
import com.edutie.backend.application.learning.course.queries.CoursesByStudentProgressQuery;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
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
@RequestMapping("api/v1/learning/courses")
@RequiredArgsConstructor
@Tag(name = "Courses Learning Controller", description = "Provides operations regarding courses in the learning context")
public class CoursesLearningController {
    private final StudentAuthorization studentAuthorization;
    private final CoursesByScienceQueryHandler coursesByScienceQueryHandler;
    private final CoursesByStudentProgressQueryHandler coursesByStudentProgressQueryHandler;


    @GetMapping
    public ResponseEntity<ApiResult<List<Course>>> getCoursesByScience(Authentication auth, @RequestParam ScienceId scienceId) {
        return new GenericRequestHandler<List<Course>>()
                .authenticate(auth)
                .authorize(studentAuthorization)
                .handle((userId) -> coursesByScienceQueryHandler.handle(
                        new CoursesByScienceQuery().scienceId(scienceId)
                ));
    }

    @GetMapping("/progressed")
    public ResponseEntity<ApiResult<List<Course>>> getCoursesByStudentProgress(Authentication auth) {
        return new GenericRequestHandler<List<Course>>()
                .authenticate(auth)
                .authorize(studentAuthorization)
                .handle((userId) -> coursesByStudentProgressQueryHandler.handle(
                        new CoursesByStudentProgressQuery().studentUserId(userId)
                ));
    }
}
