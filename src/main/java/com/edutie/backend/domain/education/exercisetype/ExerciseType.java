package com.edutie.backend.domain.education.exercisetype;

import java.util.List;
import java.util.ArrayList;

import com.edutie.backend.domain.common.base.EducatorCreatedAuditableEntity;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.exercisetype.identities.ExerciseTypeId;
import com.edutie.backend.domain.education.exercisetype.entities.ReportTemplateParagraph;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import lombok.Data;
import lombok.EqualsAndHashCode;
import validation.WrapperResult;

/**
 * Class responsible for defining exercise types which differ in utilized skills.
 * Example types would be Problem-based, case-scenario, inquiry-based, etc.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class ExerciseType extends EducatorCreatedAuditableEntity<ExerciseTypeId> {
    private String name;
    @Embedded
    @AttributeOverride(name = "text", column = @Column(name = "description"))
    private PromptFragment description;
    @OneToMany
    @OrderBy("ordinal")
    List<ReportTemplateParagraph> reportTemplate = new ArrayList<>();

    /**
     * Recommended constructor for exercise type associating it with an educator
     *
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