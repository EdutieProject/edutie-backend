package com.edutie.backend.domain.education.exercisetype;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.exercisetype.identities.ExerciseTypeId;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import validation.WrapperResult;

/**
 * Class responsible for defining exercise types which differ in utilized skills.
 * Example types would be Problem-based, case-scenario, inquiry-based, etc.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class ExerciseType extends AuditableEntityBase<ExerciseTypeId> {
    private String name;
    @Embedded
    @AttributeOverride(name = "text", column = @Column(name = "description"))
    private PromptFragment description;
    @ManyToOne(targetEntity = Educator.class, fetch = FetchType.EAGER)
    @Setter(AccessLevel.PRIVATE)
    private Educator educator;

    /**
     * Recommended constructor for exercise type associating it with an educator
     * @param educator Educator profile reference
     * @return Wrapper Result of Exercise Type
     */
    public static WrapperResult<ExerciseType> create(Educator educator) {
        ExerciseType exerciseType = new ExerciseType();
        exerciseType.setId(new ExerciseTypeId());
        exerciseType.setCreatedBy(educator.getOwnerUserId());
        exerciseType.setEducator(educator);
        return WrapperResult.successWrapper(exerciseType);
    }
}