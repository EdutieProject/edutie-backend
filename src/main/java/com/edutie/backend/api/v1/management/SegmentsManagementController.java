package com.edutie.backend.api.v1.management;

import com.edutie.backend.api.common.ApiResult;
import com.edutie.backend.api.common.GenericRequestHandler;
import com.edutie.backend.application.management.segment.*;
import com.edutie.backend.application.management.segment.commands.*;
import com.edutie.backend.application.management.segment.queries.CreatedSegmentsQuery;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.infrastucture.authorization.educator.EducatorAuthorization;
import io.swagger.v3.oas.annotations.tags.Tag;
import validation.Result;
import org.springframework.http.*;
import org.springframework.security.core.*;
import org.springframework.web.bind.annotation.*;
import lombok.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/management/segments")
@RequiredArgsConstructor
@Tag(name = "Segments Management Controller", description = "Provides operations regarding segments in the management context")
public class SegmentsManagementController {
	private final CreateSegmentCommandHandler createSegmentCommandHandler;
	private final ModifySegmentCommandHandler modifySegmentCommandHandler;
	private final RemoveSegmentCommandHandler removeSegmentCommandHandler;
	private final CreatedSegmentsQueryHandler createdSegmentsQueryHandler;
	private final EducatorAuthorization educatorAuthorization;

	@GetMapping
	public ResponseEntity<ApiResult<List<Segment>>> getCreatedSegments(Authentication auth) {
		return new GenericRequestHandler<List<Segment>>().authenticate(auth).authorize(educatorAuthorization).handle((userId) -> createdSegmentsQueryHandler.handle(new CreatedSegmentsQuery().educatorUserId(userId)));
	}

	@PostMapping
	public ResponseEntity<ApiResult<Segment>> createSegment(Authentication auth, @RequestBody CreateSegmentCommand command) {
		return new GenericRequestHandler<Segment>().authenticate(auth).authorize(educatorAuthorization).handle((userId) -> createSegmentCommandHandler.handle(command.educatorUserId(userId)));
	}

	@PatchMapping
	public ResponseEntity<ApiResult<Result>> modifySegment(Authentication auth, @RequestBody ModifySegmentCommand command) {
		return new GenericRequestHandler<Result>().authenticate(auth).authorize(educatorAuthorization).handle((userId) -> modifySegmentCommandHandler.handle(command.educatorUserId(userId)));
	}

	@DeleteMapping
	public ResponseEntity<ApiResult<Result>> modifySegment(Authentication auth, @RequestBody RemoveSegmentCommand command) {
		return new GenericRequestHandler<Result>().authenticate(auth).authorize(educatorAuthorization).handle((userId) -> removeSegmentCommandHandler.handle(command.educatorUserId(userId)));
	}
}
