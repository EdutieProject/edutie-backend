package com.edutie.edutiebackend.domain.student.validation.exceptions;

public class InvalidSchoolStageException extends Exception {
    public InvalidSchoolStageException()
    {
        super();
    }

    public InvalidSchoolStageException(String message)
    {
        super(message);
    }
}
