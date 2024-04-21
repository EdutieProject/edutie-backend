package com.edutie.backend.domain.learner.learningresult;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.learner.learningresult.identities.LearningResultId;
import com.edutie.backend.domain.learner.learningresult.valueobjects.Feedback;
import com.edutie.backend.domain.learner.student.Student;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

/**
 * A result of learning activities that is used
 * to adapt students learning parameters. Even though it is
 * adjusted for modifications, it should stay immutable and be
 * used only as a learning history fragment.
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class LearningResult extends AuditableEntityBase<LearningResultId> {
    @ManyToOne(targetEntity = Segment.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "lesson_segment_id")
    @Setter(AccessLevel.PRIVATE)
    @JsonIgnore
    private Segment segment;
    @Setter(AccessLevel.PRIVATE)
    @ManyToOne(targetEntity = Student.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Student student;
    private String reportText;
    @Embedded
    private Feedback feedback = new Feedback();

    /**
     * Recommended constructor associating learning result with Student and lesson segment
     *
     * @param student student reference
     * @param segment lesson segment reference
     * @return new Learning Result
     */
    public static LearningResult create(Student student, Segment segment) {
        LearningResult learningResult = new LearningResult();
        learningResult.setId(new LearningResultId());
        learningResult.setCreatedBy(student.getOwnerUserId());
        learningResult.setStudent(student);
        learningResult.setSegment(segment);
        return learningResult;
    }

}
