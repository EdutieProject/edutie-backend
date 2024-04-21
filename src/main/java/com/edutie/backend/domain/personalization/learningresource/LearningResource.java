package com.edutie.backend.domain.personalization.learningresource;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.learner.student.Student;
import com.edutie.backend.domain.personalization.learningresource.identities.LearningResourceId;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

/**
 * A singular form of learning in the application.
 * This is the resource which is used by the learner to exercise
 * his knowledge. It may be exclusively generated for a given student.
 */
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
//TODO: (DOMAIN) add hints
//TODO: rework according to docs.
public class LearningResource extends AuditableEntityBase<LearningResourceId> {
    @ManyToOne(targetEntity = Segment.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "lesson_segment_id")
    @Setter(AccessLevel.PRIVATE)
    private Segment segment;
    @ManyToOne(targetEntity = Student.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    @Setter(AccessLevel.PRIVATE)
    private Student student;

    /**
     * Recommended constructor associating learning resource with a student (creation invoker) and a lesson segment.
     *
     * @param student student profile reference
     * @param segment lesson segment reference
     * @return Learning Resource
     */
    public static LearningResource create(Student student, Segment segment) {
        LearningResource learningResource = new LearningResource();
        learningResource.setId(new LearningResourceId());
        learningResource.setCreatedBy(student.getOwnerUserId());
        learningResource.setStudent(student);
        learningResource.setSegment(segment);
        return learningResource;
    }

}
