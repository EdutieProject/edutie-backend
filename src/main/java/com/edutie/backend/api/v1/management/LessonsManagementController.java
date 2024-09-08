package com.edutie.backend.api.v1.management;

import com.edutie.backend.api.common.ApiResult;
import com.edutie.backend.api.common.GenericRequestHandler;
import com.edutie.backend.application.management.lesson.*;
import com.edutie.backend.application.management.lesson.commands.*;
import com.edutie.backend.application.management.lesson.queries.CreatedLessonsQuery;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.infrastucture.authorization.educator.EducatorAuthorization;
import io.swagger.v3.oas.annotations.tags.Tag;
import validation.Result;
import org.springframework.http.*;
import org.springframework.security.core.*;
import org.springframework.web.bind.annotation.*;
import lombok.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/management/lessons")
@RequiredArgsConstructor
@Tag(name = "Lessons Management Controller", description = "Provides operations regarding lessons in the management context")
public class LessonsManagementController {
	private final CreateLessonCommandHandler createLessonCommandHandler;
	private final ModifyLessonCommandHandler modifyLessonCommandHandler;
	private final RemoveLessonCommandHandler removeLessonCommandHandler;
	private final CreatedLessonsQueryHandler createdLessonsQueryHandler;
	private final EducatorAuthorization educatorAuthorization;

	@GetMapping
	public ResponseEntity<ApiResult<List<Lesson>>> getCreatedLessons(Authentication auth) {
		return new GenericRequestHandler<List<Lesson>>().authenticate(auth).authorize(educatorAuthorization).handle((userId) -> createdLessonsQueryHandler.handle(new CreatedLessonsQuery().educatorUserId(userId)));
	}

	@PostMapping
	public ResponseEntity<ApiResult<Lesson>> createLesson(Authentication auth, @RequestBody CreateLessonCommand command) {
		return new GenericRequestHandler<Lesson>().authenticate(auth).authorize(educatorAuthorization).handle((userId) -> createLessonCommandHandler.handle(command.educatorUserId(userId)));
	}

	@PatchMapping
	public ResponseEntity<ApiResult<Result>> modifyLesson(Authentication auth, @RequestBody ModifyLessonCommand command) {
		return new GenericRequestHandler<Result>().authenticate(auth).authorize(educatorAuthorization).handle((userId) -> modifyLessonCommandHandler.handle(command.educatorUserId(userId)));
	}

	@DeleteMapping
	public ResponseEntity<ApiResult<Result>> removeLesson(Authentication auth, @RequestBody RemoveLessonCommand command) {
		return new GenericRequestHandler<Result>().authenticate(auth).authorize(educatorAuthorization).handle((userId) -> removeLessonCommandHandler.handle(command.educatorUserId(userId)));
	}
}
