package com.edutie.edutiebackend.domain.student.exceptions;

/**
 * Exception thrown when provided trait specification
 * cannot match any tracker.
 */
public class TraitTrackerNotFoundException extends Exception {
    public TraitTrackerNotFoundException()
    {
        super();
    }

    public TraitTrackerNotFoundException(String message)
    {
        super(message);
    }
}
