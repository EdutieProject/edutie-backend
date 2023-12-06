package com.edutie.edutiebackend.application.services.personalization.resource.commands;

import com.edutie.edutiebackend.application.services.common.requests.AuthenticatedServiceRequest;

import java.util.UUID;

public class GenerateResourceCommand extends AuthenticatedServiceRequest {
    public UUID lessonId;
}
