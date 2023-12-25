package com.edutie.edutiebackend.domain.core.student.exceptions;

/**
 * Exception thrown when provided trait specification
 * cannot match any tracker.
 */
public class TraitTrackerNotFoundException extends RuntimeException {
    public TraitTrackerNotFoundException()
    {
        super();
    }

    public TraitTrackerNotFoundException(String message)
    {
        super(message);
    }
}
