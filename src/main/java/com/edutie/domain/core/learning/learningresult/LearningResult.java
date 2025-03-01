package com.edutie.domain.core.learning.learningresult;

import com.edutie.domain.core.common.base.AuditableEntityBase;
import com.edutie.domain.core.learning.learningexperience.identities.LearningExperienceId;
import com.edutie.domain.core.learning.learningresult.entities.LearningEvaluation;
import com.edutie.domain.core.learning.learningresult.entities.submission.base.SolutionSubmission;
import com.edutie.domain.core.learning.learningresult.identities.LearningResultId;
import com.edutie.domain.core.learning.student.identities.StudentId;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A result of learning activities that is used
 * to adapt students learning parameters. Even though it is
 * adjusted for modifications, it should stay immutable and be
 * used only as a learning history fragment.
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@MappedSuperclass
public class LearningResult<TSolutionSubmission extends SolutionSubmission> extends AuditableEntityBase<LearningResultId> {
    @OneToOne(targetEntity = LearningEvaluation.class, fetch = FetchType.EAGER)
    private LearningEvaluation learningEvaluation = new LearningEvaluation();
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private TSolutionSubmission solutionSubmission;
    @Embedded
    @AttributeOverride(name = "identifierValue", column = @Column(name = "learning_experience_id"))
    private LearningExperienceId learningExperienceId;
    @Embedded
    @AttributeOverride(name = "identifierValue", column = @Column(name = "student_id"))
    private StudentId studentId;
}
