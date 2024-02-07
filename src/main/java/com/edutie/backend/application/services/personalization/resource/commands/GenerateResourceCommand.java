package com.edutie.backend.application.services.personalization.resource.commands;

import java.util.UUID;

import com.edutie.backend.application.services.common.requests.AuthenticatedServiceRequest;

public class GenerateResourceCommand extends AuthenticatedServiceRequest {
    public UUID lessonId;
}
