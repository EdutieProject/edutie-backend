package com.edutie.backend.api.v1.learning;

import com.edutie.backend.api.common.ApiResult;
import com.edutie.backend.api.common.GenericRequestHandler;
import com.edutie.backend.application.learning.studyprogram.AccessibleSciencesQueryHandler;
import com.edutie.backend.application.learning.studyprogram.CoursesByScienceQueryHandler;
import com.edutie.backend.application.learning.studyprogram.ViewLessonsFromCourseQueryHandler;
import com.edutie.backend.application.learning.studyprogram.ViewSegmentsFromLessonQueryHandler;
import com.edutie.backend.application.learning.studyprogram.queries.AccessibleSciencesQuery;
import com.edutie.backend.application.learning.studyprogram.queries.CoursesByScienceQuery;
import com.edutie.backend.application.learning.studyprogram.queries.ViewLessonsFromCourseQuery;
import com.edutie.backend.application.learning.studyprogram.queries.ViewSegmentsFromLessonQuery;
import com.edutie.backend.application.learning.studyprogram.viewmodels.LessonView;
import com.edutie.backend.application.learning.studyprogram.viewmodels.SegmentView;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import com.edutie.backend.domain.studyprogram.science.Science;
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
@RequestMapping("api/v1/learning/study-program")
@RequiredArgsConstructor
@Tag(name = "Study Program Learning Controller", description = "Provides operations regarding study program in the learning context")
public class StudyProgramLearningController {
    private final StudentAuthorization studentAuthorization;
    private final AccessibleSciencesQueryHandler accessibleSciencesQueryHandler;
    private final CoursesByScienceQueryHandler coursesByScienceQueryHandler;
    private final ViewLessonsFromCourseQueryHandler viewLessonsFromCourseQueryHandler;
    private final ViewSegmentsFromLessonQueryHandler viewSegmentsFromLessonQueryHandler;

    @GetMapping("/sciences")
    public ResponseEntity<ApiResult<List<Science>>> getAccessibleSciences(Authentication auth) {
        return new GenericRequestHandler<List<Science>>()
                .authenticate(auth)
                .authorize(studentAuthorization)
                .handle(() -> accessibleSciencesQueryHandler.handle(new AccessibleSciencesQuery()));

    }

    @GetMapping("/courses")
    public ResponseEntity<ApiResult<List<Course>>> getCoursesByScience(Authentication auth, @RequestParam ScienceId scienceId) {
        return new GenericRequestHandler<List<Course>>()
                .authenticate(auth)
                .authorize(studentAuthorization)
                .handle((userId) -> coursesByScienceQueryHandler.handle(
                        new CoursesByScienceQuery().scienceId(scienceId)
                ));
    }

    @GetMapping("/lessons")
    public ResponseEntity<ApiResult<List<LessonView>>> getLessonsForStudentFromCourse(Authentication auth, @RequestParam CourseId courseId) {
        return new GenericRequestHandler<List<LessonView>>()
                .authenticate(auth)
                .authorize(studentAuthorization)
                .handle((userId) -> viewLessonsFromCourseQueryHandler.handle(
                        new ViewLessonsFromCourseQuery().studentUserId(userId).courseId(courseId)
                ));
    }

    @GetMapping("/segments")
    public ResponseEntity<ApiResult<List<SegmentView>>> getSegmentsForStudentFromLesson(Authentication auth, @RequestParam LessonId lessonId) {
        return new GenericRequestHandler<List<SegmentView>>()
                .authenticate(auth)
                .authorize(studentAuthorization)
                .handle((userId) -> viewSegmentsFromLessonQueryHandler.handle(
                        new ViewSegmentsFromLessonQuery().studentUserId(userId).lessonId(lessonId)
                ));
    }
}
