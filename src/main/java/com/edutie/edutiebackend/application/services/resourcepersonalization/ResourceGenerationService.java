package com.edutie.edutiebackend.application.services.resourcepersonalization;



import com.edutie.edutiebackend.domain.core.learningresource.LearningResource;
import com.edutie.edutiebackend.domain.core.lessonsegment.identities.LessonSegmentId;
import com.edutie.edutiebackend.domain.core.student.identities.StudentId;

/**
 * Interface responsible for creating learning resources
 */
public interface ResourceGenerationService {


    /**
     * Provides a learning resource optimized for a given student.
     * Implementation uses other methods that this
     * @param studentId student identifier
     * @param lessonSegmentId lesson segment identifier
     * @return created Learning Resource object
     */
    LearningResource createWithAI(StudentId studentId, LessonSegmentId lessonSegmentId);
}
