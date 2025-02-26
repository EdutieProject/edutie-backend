package com.edutie.domain.core.learning.solutionsubmission;

import com.edutie.domain.core.common.base.AuditableEntityBase;
import com.edutie.domain.core.personalization.common.AbsoluteDefinition;
import com.edutie.domain.core.learning.learningexperience.identities.LearningResourceId;
import com.edutie.backend.domain.personalization.learningresourcedefinition.enums.DefinitionType;
import com.edutie.domain.core.learning.solutionsubmission.identities.SolutionSubmissionId;
import com.edutie.domain.core.learning.student.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A submitted solution for the learning resource.
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
public class SolutionSubmission extends AuditableEntityBase<SolutionSubmissionId> implements AbsoluteDefinition {
    private int hintsRevealed = 0;
    @ManyToOne(targetEntity = Student.class, fetch = FetchType.EAGER)
    @Setter(AccessLevel.PRIVATE)
    @JsonIgnore
    private Student student;
    @Column(columnDefinition = "TEXT")
    private String reportText;
    // Metadata:
    @AttributeOverride(name = "identifierValue", column = @Column(name = "learning_resource_id"))
    private LearningResourceId learningResourceId;
    private DefinitionType learningResourceDefinitionType;

    public static SolutionSubmission create(
            Student student,
            LearningResourceId learningResourceId,
            DefinitionType definitionType,
            String reportText,
            int hintsRevealed
    ) {
        SolutionSubmission solutionSubmission = new SolutionSubmission();
        solutionSubmission.setId(new SolutionSubmissionId());
        solutionSubmission.setCreatedBy(student.getOwnerUserId());
        solutionSubmission.setStudent(student);
        solutionSubmission.setLearningResourceId(learningResourceId);
        solutionSubmission.setHintsRevealed(hintsRevealed);
        solutionSubmission.setReportText(reportText);
        solutionSubmission.setLearningResourceDefinitionType(definitionType);
        return solutionSubmission;
    }
}
