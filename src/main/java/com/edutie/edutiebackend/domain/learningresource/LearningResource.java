package com.edutie.edutiebackend.domain.learningresource;

import com.edutie.edutiebackend.domain.common.base.EntityBase;
import com.edutie.edutiebackend.domain.common.identities.LearningResourceId;
import com.edutie.edutiebackend.domain.common.identities.LessonSegmentId;
import com.edutie.edutiebackend.domain.learningresource.interfaces.ILearningActivity;
import com.edutie.edutiebackend.domain.learningresource.valueobjects.ResourceOverview;
import jakarta.persistence.Entity;

import java.util.Set;

/**
 * A singular form of learning in the application.
 * This is the resource which is used by the learner to exercise
 * his knowledge.
 * It may be fixed into the lesson segment or dynamically generated, depending
 * on lessonSegment's isDynamic field.
 */
@Entity
public class LearningResource extends EntityBase<LearningResourceId> {
    private LessonSegmentId lessonSegmentId;
    public ResourceOverview overview;
    public ILearningActivity learningActivity;
}
