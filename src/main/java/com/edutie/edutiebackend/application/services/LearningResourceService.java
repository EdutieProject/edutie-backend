package com.edutie.edutiebackend.application.services;



import com.edutie.edutiebackend.domain.core.learningresource.LearningResource;
import com.edutie.edutiebackend.domain.core.lessonsegment.LessonSegment;
import com.edutie.edutiebackend.domain.core.student.Student;

/**
 * Interface responsible for creating learning resources
 */
public interface LearningResourceService{


    /**
     * Provides a learning resource optimized for a given student.
     * Implementation uses other methods that this
     * @param student student aggregate
     * @param lessonSegment lesson segment
     * @return Learning Resource
     */
    LearningResource createFor(final Student student, final LessonSegment lessonSegment);
}
