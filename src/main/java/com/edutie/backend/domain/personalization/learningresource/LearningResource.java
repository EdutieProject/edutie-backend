package com.edutie.backend.domain.personalization.learningresource;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.personalization.learningresource.entities.Hint;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.learningresource.entities.Activity;
import com.edutie.backend.domain.personalization.learningresource.entities.Theory;
import com.edutie.backend.domain.personalization.learningresource.identities.LearningResourceId;
import com.edutie.backend.domain.personalization.student.identities.StudentId;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

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
    @Embedded
    @AttributeOverride(name = "identifierValue", column = @Column(name = "definition_id"))
    @Setter(AccessLevel.PRIVATE)
    private LearningResourceDefinitionId definitionId;
    @Embedded
    @AttributeOverride(name = "identifierValue", column = @Column(name = "student_id"))
    @Setter(AccessLevel.PRIVATE)
    private StudentId studentId;
    @OneToOne(targetEntity = Activity.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Activity activity;
    @OneToOne(targetEntity = Theory.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Theory theory;

    /**
     * Recommended constructor associating learning resource with a student (creation invoker) and a lesson segment.
     *
     * @param studentId student profile identity
     * @param definitionId resource definition reference
     * @return Learning Resource
     */
    public static LearningResource create(StudentId studentId,
                                          LearningResourceDefinitionId definitionId,
                                          String activityText,
                                          Set<Hint> hints,
                                          String theoryOverviewText,
                                          String theorySummaryText
    ) {
        LearningResource learningResource = new LearningResource();
        learningResource.setId(new LearningResourceId());
        learningResource.setStudentId(studentId);
        learningResource.setDefinitionId(definitionId);
        learningResource.setActivity(Activity.create(activityText, hints));
        learningResource.setTheory(Theory.create(theoryOverviewText, theorySummaryText));
        return learningResource;
    }


}
