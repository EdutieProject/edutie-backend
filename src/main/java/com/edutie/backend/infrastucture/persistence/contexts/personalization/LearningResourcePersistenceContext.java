package com.edutie.backend.infrastucture.persistence.contexts.personalization;

import com.edutie.backend.domain.learner.student.identities.StudentId;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.identities.LearningResourceId;
import com.edutie.backend.domain.studyprogram.lessonsegment.identities.LessonSegmentId;
import com.edutie.backend.infrastucture.persistence.contexts.base.PersistenceContext;

import java.util.List;

public interface LearningResourcePersistenceContext extends PersistenceContext<LearningResource, LearningResourceId> {
    /**
     * Retrieve all learning resources associated with given lesson segment
     * @param lessonSegmentId lesson segment id
     * @return Learning Resource list
     */
    List<LearningResource> getAllOfLessonSegmentId(LessonSegmentId lessonSegmentId);

    /**
     * Retrieve all Learning Resources associated with given student
     * @param studentId student id
     * @return Learning Resource list
     */
    List<LearningResource> getAllOfStudentId(StudentId studentId);
}
