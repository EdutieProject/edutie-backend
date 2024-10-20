package com.edutie.backend.api.v1.learning;

import com.edutie.backend.api.common.ApiResult;
import com.edutie.backend.api.common.GenericRequestHandler;
import com.edutie.backend.application.learning.studyprogram.*;
import com.edutie.backend.application.learning.studyprogram.queries.*;
import com.edutie.backend.application.learning.studyprogram.viewmodels.LessonView;
import com.edutie.backend.application.learning.studyprogram.viewmodels.SegmentView;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.course.identities.CourseId;
import com.edutie.backend.domain.studyprogram.lesson.identities.LessonId;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.science.identities.ScienceId;
import com.edutie.backend.infrastucture.authorization.student.StudentAuthorization;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/learning/study-program")
@RequiredArgsConstructor
@Tag(name = "Study Program Learning Controller", description = "Provides operations regarding study program in the learning context")
public class StudyProgramLearningController {
    private final StudentAuthorization studentAuthorization;
    private final AccessibleSciencesQueryHandler accessibleSciencesQueryHandler;
    private final CoursesByScienceQueryHandler coursesByScienceQueryHandler;
    private final CourseByIdQueryHandler courseByIdQueryHandler;
    private final ViewLessonsFromCourseQueryHandler viewLessonsFromCourseQueryHandler;
    private final ViewSegmentsFromLessonQueryHandler viewSegmentsFromLessonQueryHandler;

    @GetMapping("/sciences/retrieve-accessible")
    @Operation(description = "Retrieves all accessible sciences")
    public ResponseEntity<ApiResult<List<Science>>> getAccessibleSciences(Authentication auth) {
        return new GenericRequestHandler<List<Science>>()
                .authenticate(auth)
                .authorize(studentAuthorization)
                .handle(() -> accessibleSciencesQueryHandler.handle(
                        new AccessibleSciencesQuery()
                ));

    }

    @GetMapping("/courses/retrieve-by-science")
    @Operation(description = "Retrieves all courses associated with a given science")
    public ResponseEntity<ApiResult<List<Course>>> getCoursesByScience(Authentication auth, @RequestParam ScienceId scienceId) {
        return new GenericRequestHandler<List<Course>>()
                .authenticate(auth)
                .authorize(studentAuthorization)
                .handle((userId) -> coursesByScienceQueryHandler.handle(
                        new CoursesByScienceQuery().scienceId(scienceId)
                ));
    }

    @GetMapping("/courses/{courseId}")
    @Operation(description = "Retrieves a course by its identifier")
    public ResponseEntity<ApiResult<Course>> getCourseById(Authentication auth, @PathVariable CourseId courseId) {
        return new GenericRequestHandler<Course>()
                .authenticate(auth)
                .authorize(studentAuthorization)
                .handle((userId) -> courseByIdQueryHandler.handle(
                        new CourseByIdQuery().courseId(courseId).studentUserId(userId)
                ));
    }

    @GetMapping("/lessons/retrieve-by-course")
    @Operation(description = "Retrieves lesson views for the student invoking the flow from the given course")
    public ResponseEntity<ApiResult<List<LessonView>>> getLessonsForStudentFromCourse(Authentication auth, @RequestParam CourseId courseId) {
        return new GenericRequestHandler<List<LessonView>>()
                .authenticate(auth)
                .authorize(studentAuthorization)
                .handle((userId) -> viewLessonsFromCourseQueryHandler.handle(
                        new ViewLessonsFromCourseQuery().studentUserId(userId).courseId(courseId)
                ));
    }

    @GetMapping("/segments/retrieve-by-lesson")
    @Operation(description = "Retrieves segment views for student invoking the flow from the given lesson")
    public ResponseEntity<ApiResult<List<SegmentView>>> getSegmentsForStudentFromLesson(Authentication auth, @RequestParam LessonId lessonId) {
        return new GenericRequestHandler<List<SegmentView>>()
                .authenticate(auth)
                .authorize(studentAuthorization)
                .handle((userId) -> viewSegmentsFromLessonQueryHandler.handle(
                        new ViewSegmentsFromLessonQuery().studentUserId(userId).lessonId(lessonId)
                ));
    }
}
