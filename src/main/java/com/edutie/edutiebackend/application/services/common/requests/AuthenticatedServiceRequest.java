package com.edutie.edutiebackend.application.services.common.requests;

import java.util.UUID;

/**
 * A common service request that contains authentication-related data.
 * Meant to be extensible by concrete command and query classes.
 */
public class AuthenticatedServiceRequest extends ServiceRequest {
    /**
     *
     */
    UUID userId = null;
}
