package com.edutie.backend.domain.personalization.learningresource;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.personalization.learningresource.entities.Activity;
import com.edutie.backend.domain.personalization.learningresource.entities.Hint;
import com.edutie.backend.domain.personalization.learningresource.entities.ProblemDetail;
import com.edutie.backend.domain.personalization.learningresource.entities.Theory;
import com.edutie.backend.domain.personalization.learningresource.identities.LearningResourceId;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.LearningResourceGenerationSchema;
import com.edutie.backend.domain.personalization.student.identities.StudentId;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
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
    @OneToMany(targetEntity = ProblemDetail.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ProblemDetail> problemDetails = new HashSet<>();

    /**
     * Recommended constructor that creates learning resource from L.R.G.S. and other different details.
     *
     * @param generationSchema   generation schema
     * @param activityText       text of activity
     * @param hints              hints set
     * @param theoryOverviewText theory overview text
     * @param theorySummaryText  theory summary text
     * @param problemDetails     problem details text
     * @return new Learning Resource
     */
    public static LearningResource create(LearningResourceGenerationSchema generationSchema,
                                          String activityText,
                                          Set<Hint> hints,
                                          String theoryOverviewText,
                                          String theorySummaryText,
                                          Set<ProblemDetail> problemDetails
    ) {
        LearningResource learningResource = new LearningResource();
        learningResource.setId(new LearningResourceId());
        learningResource.setCreatedBy(generationSchema.getStudent().getOwnerUserId());
        learningResource.setStudentId(generationSchema.getStudent().getId());
        learningResource.setDefinitionId(generationSchema.getLearningResourceDefinition().getId());
        learningResource.setActivity(Activity.create(activityText, hints));
        learningResource.setTheory(Theory.create(theoryOverviewText, theorySummaryText));
        learningResource.setProblemDetails(problemDetails);
        return learningResource;
    }
}
