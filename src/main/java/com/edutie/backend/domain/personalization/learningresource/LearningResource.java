package com.edutie.backend.domain.personalization.learningresource;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.learningresource.entities.Activity;
import com.edutie.backend.domain.personalization.learningresource.entities.Theory;
import com.edutie.backend.domain.personalization.learningresource.identities.LearningResourceId;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
public class LearningResource extends AuditableEntityBase<LearningResourceId> {
    @ManyToOne(targetEntity = Segment.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "lesson_segment_id")
    @Setter(AccessLevel.PRIVATE)
    @JsonIgnore
    private Segment segment;
    @ManyToOne(targetEntity = Student.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    @Setter(AccessLevel.PRIVATE)
    @JsonIgnore
    private Student student;
    @OneToOne(targetEntity = Activity.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Activity activity = Activity.create();
    @OneToOne(targetEntity = Theory.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Theory theory = Theory.create();

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
        learningResource.activity.setExerciseType(segment.getExerciseType());
        return learningResource;
    }

    public void addActivityHint(String hintText) {
        activity.addHint(hintText);
    }

    public void assignActivityDetails(String activityText, String hintAdditionalDescription) {
        activity.setActivityText(activityText);
    }

    public void assignTheoryDetails(String theoryOverview, String theorySummary) {
        theory.setOverview(theoryOverview);
        theory.setSummary(theorySummary);
    }

}
