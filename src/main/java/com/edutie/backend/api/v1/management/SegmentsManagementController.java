package com.edutie.backend.api.v1.management;

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
import com.edutie.backend.api.v1.authentication.AuthenticationPlaceholder;
import com.edutie.backend.infrastucture.authorization.educator.EducatorAuthorization;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.JsonWebToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import validation.Result;
import validation.WrapperResult;

@RestController
@RequestMapping("/api/v1/management/segments")
@RequiredArgsConstructor
public class SegmentsManagementController {
    private final AuthenticationPlaceholder authentication;
    private final CreateSegmentCommandHandler createSegmentCommandHandler;
    private final ModifySegmentCommandHandler modifySegmentCommandHandler;
    private final RemoveSegmentCommandHandler removeSegmentCommandHandler;
    private final CreatedSegmentsQueryHandler createdSegmentsQueryHandler;
    private final EducatorAuthorization educatorAuthorization;

    @GetMapping
    public ResponseEntity<?> getCreatedSegments() {
        UserId actionUserId = authentication.authenticateUser(new JsonWebToken());
        return new GenericRequestHandler<WrapperResult<?>, EducatorAuthorization>()
                .authorize(actionUserId, educatorAuthorization)
                .handle(() -> createdSegmentsQueryHandler.handle(new CreatedSegmentsQuery(actionUserId)));
    }

    @PostMapping
    public ResponseEntity<?> createSegment(@RequestBody CreateSegmentCommand command) {
        UserId actionUserId = authentication.authenticateUser(new JsonWebToken());
        return new GenericRequestHandler<WrapperResult<?>, EducatorAuthorization>()
                .authorize(actionUserId, educatorAuthorization)
                .handle(() -> createSegmentCommandHandler.handle(command.educatorUserId(actionUserId)));
    }

    @PatchMapping
    public ResponseEntity<?> modifySegment(@RequestBody ModifySegmentCommand command) {
        UserId actionUserId = authentication.authenticateUser(new JsonWebToken());
        return new GenericRequestHandler<Result, EducatorAuthorization>()
                .authorize(actionUserId, educatorAuthorization)
                .handle(() -> modifySegmentCommandHandler.handle(command.educatorUserId(actionUserId)));
    }

    @DeleteMapping
    public ResponseEntity<?> modifySegment(@RequestBody RemoveSegmentCommand command) {
        UserId actionUserId = authentication.authenticateUser(new JsonWebToken());
        return new GenericRequestHandler<Result, EducatorAuthorization>()
                .authorize(actionUserId, educatorAuthorization)
                .handle(() -> removeSegmentCommandHandler.handle(command.educatorUserId(actionUserId)));
    }
}
