package com.edutie.edutiebackend.application.services.common.requests;

import java.util.UUID;

public class AuthenticatedRequest extends AuditableRequest{
    UUID userId = null;
}
