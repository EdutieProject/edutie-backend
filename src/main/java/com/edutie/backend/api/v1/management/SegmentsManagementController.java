package com.edutie.backend.api.v1.management;

import com.edutie.backend.api.common.ApiResult;
import com.edutie.backend.api.common.GenericRequestHandler;
import com.edutie.backend.application.management.segment.CreateSegmentCommandHandler;
import com.edutie.backend.application.management.segment.CreatedSegmentsQueryHandler;
import com.edutie.backend.application.management.segment.ModifySegmentCommandHandler;
import com.edutie.backend.application.management.segment.RemoveSegmentCommandHandler;
import com.edutie.backend.application.management.segment.commands.CreateSegmentCommand;
import com.edutie.backend.application.management.segment.commands.ModifySegmentCommand;
import com.edutie.backend.application.management.segment.commands.RemoveSegmentCommand;
import com.edutie.backend.application.management.segment.queries.CreatedSegmentsQuery;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.api.authentication.AuthenticationPlaceholder;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.infrastucture.authorization.educator.EducatorAuthorization;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import validation.Result;

import java.util.List;

@RestController
@RequestMapping("/api/v1/management/segments")
@RequiredArgsConstructor
@Tag(name = "Segments Management Controller", description = "Provides operations regarding segments in the management context")
public class SegmentsManagementController {
    private final AuthenticationPlaceholder authentication;
    private final CreateSegmentCommandHandler createSegmentCommandHandler;
    private final ModifySegmentCommandHandler modifySegmentCommandHandler;
    private final RemoveSegmentCommandHandler removeSegmentCommandHandler;
    private final CreatedSegmentsQueryHandler createdSegmentsQueryHandler;
    private final EducatorAuthorization educatorAuthorization;

    @GetMapping
    public ResponseEntity<ApiResult<List<Segment>>> getCreatedSegments(Authentication auth) {
        UserId actionUserId = authentication.authenticateUser(auth);
        return new GenericRequestHandler<List<Segment>, EducatorAuthorization>()
                .authorize(actionUserId, educatorAuthorization)
                .handle(() -> createdSegmentsQueryHandler.handle(new CreatedSegmentsQuery().educatorUserId(actionUserId)));
    }

    @PostMapping
    public ResponseEntity<ApiResult<Segment>> createSegment(Authentication auth, @RequestBody CreateSegmentCommand command) {
        UserId actionUserId = authentication.authenticateUser(auth);
        return new GenericRequestHandler<Segment, EducatorAuthorization>()
                .authorize(actionUserId, educatorAuthorization)
                .handle(() -> createSegmentCommandHandler.handle(command.educatorUserId(actionUserId)));
    }

    @PatchMapping
    public ResponseEntity<ApiResult<Result>> modifySegment(Authentication auth, @RequestBody ModifySegmentCommand command) {
        UserId actionUserId = authentication.authenticateUser(auth);
        return new GenericRequestHandler<Result, EducatorAuthorization>()
                .authorize(actionUserId, educatorAuthorization)
                .handle(() -> modifySegmentCommandHandler.handle(command.educatorUserId(actionUserId)));
    }

    @DeleteMapping
    public ResponseEntity<ApiResult<Result>> modifySegment(Authentication auth, @RequestBody RemoveSegmentCommand command) {
        UserId actionUserId = authentication.authenticateUser(auth);
        return new GenericRequestHandler<Result, EducatorAuthorization>()
                .authorize(actionUserId, educatorAuthorization)
                .handle(() -> removeSegmentCommandHandler.handle(command.educatorUserId(actionUserId)));
    }
}
