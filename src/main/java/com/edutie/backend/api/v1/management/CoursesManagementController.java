package com.edutie.backend.api.v1.management;

import com.edutie.backend.api.common.ApiResult;
import com.edutie.backend.api.common.GenericRequestHandler;
import com.edutie.backend.application.management.course.*;
import com.edutie.backend.application.management.course.commands.*;
import com.edutie.backend.application.management.course.queries.CreatedCoursesQuery;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.infrastructure.authorization.educator.EducatorAuthorization;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import validation.Result;
import org.springframework.http.*;
import org.springframework.security.core.*;
import org.springframework.web.bind.annotation.*;
import lombok.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/management/courses")
@RequiredArgsConstructor
@Tag(name = "Courses Management Controller", description = "Provides operations regarding courses in the management context")
public class CoursesManagementController {
	private final CreateCourseCommandHandler createCourseCommandHandler;
	private final ModifyCourseCommandHandler modifyCourseCommandHandler;
	private final RemoveCourseCommandHandler removeCourseCommandHandler;
	private final CreatedCoursesQueryHandler createdCoursesQueryHandler;
	private final EducatorAuthorization educatorAuthorization;

	@GetMapping("/retrieve")
	@Operation(description = "Retrieves courses created by an educator invoking the flow")
	public ResponseEntity<ApiResult<List<Course>>> getCreatedCourses(Authentication auth) {
		return new GenericRequestHandler<List<Course>>().authenticate(auth).authorize(educatorAuthorization).handle((userId) -> createdCoursesQueryHandler.handle(new CreatedCoursesQuery().educatorUserId(userId)));
	}

	@PostMapping("/create")
	@Operation(description = "Creates using a given command. May be performed by an educator only")
	public ResponseEntity<ApiResult<Course>> createCourse(Authentication auth, @RequestBody CreateCourseCommand command) {
		return new GenericRequestHandler<Course>().authenticate(auth).authorize(educatorAuthorization).handle((userId) -> createCourseCommandHandler.handle(command.educatorUserId(userId)));
	}

	@PatchMapping("/modify")
	@Operation(description = "Modifies a course using the specified command. May be performed by the author of the course")
	public ResponseEntity<ApiResult<Result>> modifyCourse(Authentication auth, @RequestBody ModifyCourseCommand command) {
		return new GenericRequestHandler<Result>().authenticate(auth).authorize(educatorAuthorization).handle((userId) -> modifyCourseCommandHandler.handle(command.educatorUserId(userId)));
	}

	@DeleteMapping("/remove")
	@Operation(description = "Removes a course using the specified command. May be performed by the author of the course")
	public ResponseEntity<ApiResult<Result>> removeCourse(Authentication auth, @RequestBody RemoveCourseCommand command) {
		return new GenericRequestHandler<Result>().authenticate(auth).authorize(educatorAuthorization).handle((userId) -> removeCourseCommandHandler.handle(command.educatorUserId(userId)));
	}

}
