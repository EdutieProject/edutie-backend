package com.edutie.edutiebackend.domain.learningresource;

import com.edutie.edutiebackend.domain.common.base.EntityBase;
import com.edutie.edutiebackend.domain.common.identities.LearningResourceId;
import com.edutie.edutiebackend.domain.common.identities.LessonSegmentId;
import com.edutie.edutiebackend.domain.common.identities.StudentId;
import com.edutie.edutiebackend.domain.learningresource.valueobjects.Exercise;
import com.edutie.edutiebackend.domain.learningresource.valueobjects.Flowchart;
import com.edutie.edutiebackend.domain.learningresource.valueobjects.ResourceOverview;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * A singular form of learning in the application.
 * This is the resource which is used by the learner to exercise
 * his knowledge. It is generated for each student exclusively.
 * It may be fixed into the lesson segment or dynamically generated, depending
 * on lessonSegment's isDynamic field.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class LearningResource extends EntityBase<LearningResourceId> {
    //TODO!!!: remove studentId and replace it with optimization detail entities or vo.
    private StudentId studentId;
    private LessonSegmentId lessonSegmentId;
    private ResourceOverview overview;
    private Exercise exercise;
    private Flowchart flowchart;
}
