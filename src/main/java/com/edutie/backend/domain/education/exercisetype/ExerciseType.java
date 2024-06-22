package com.edutie.backend.domain.education.exercisetype;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import com.edutie.backend.domain.common.base.EducatorCreatedAuditableEntity;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.exercisetype.identities.ExerciseTypeId;
import com.edutie.backend.domain.education.exercisetype.identities.ReportTemplateParagraphId;
import com.edutie.backend.domain.education.exercisetype.entities.ReportTemplateParagraph;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import validation.Error;
import validation.Result;
import validation.WrapperResult;

/**
 * Class responsible for defining exercise types which differ in utilized
 * skills.
 * Example types would be Problem-based, case-scenario, inquiry-based, etc.
 */
@NoArgsConstructor
@Getter
@Setter
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
     * Appends new report template paragraph at the end of report template
     * @param title title of the paragraph
     * @param text text of the paragraph
     * @return Result object
     */
    public Result appendReportTemplateParagraph(String title, String text) {
        ReportTemplateParagraph newParagraph = ReportTemplateParagraph.create(title, text);
        newParagraph.setOrdinal(reportTemplate.size());
        reportTemplate.add(newParagraph);
        return Result.success();
    }

    /**
     * Moves report template paragraph & restructures the paragraph order.
     * @param paragraphId id of a paragraph
     * @param newIndex desired index of a paragraph
     * @return Result object
     */
    public Result moveReportTemplateParagraph(ReportTemplateParagraphId paragraphId, short newIndex) {
        Optional<ReportTemplateParagraph> reportTemplateParagraph = reportTemplate.stream().filter(o -> o.getId().equals(paragraphId))
                .findFirst();
        if (reportTemplateParagraph.isEmpty())
            return Result.failure(new Error("TODO!", this.getClass().getSimpleName()));
        if (newIndex > reportTemplate.size() - 1)
            return Result.failure(new Error("TODO!", this.getClass().getSimpleName()));
        ReportTemplateParagraph paragraphToMove = reportTemplateParagraph.get();
        reportTemplate.remove(paragraphToMove);
        reportTemplate.add(newIndex, paragraphToMove);
        for (int i = 0; i < reportTemplate.size(); i++) {
            reportTemplate.get(i).setOrdinal(i);
        }
        return Result.success();
    }

    /**
     * Removes report template paragraph
     * @param paragraphIndex paragraph index
     * @return
     */
    public Result removeReportTemplateParagraph(int paragraphIndex) {
        if (paragraphIndex > reportTemplate.size())
            return Result.failure(new Error("TODO!", this.getClass().getSimpleName()));
        reportTemplate.remove(paragraphIndex);
        for (int i = paragraphIndex; i < reportTemplate.size(); i++) {
            reportTemplate.get(i).setOrdinal(i);
        }
        return Result.success();
    }

    /**
     * Recommended constructor for exercise type associating it with an educator
     *
     * @param educator Educator profile reference
     * @return Wrapper Result of Exercise Type
     */
    public static ExerciseType create(Educator educator) {
        ExerciseType exerciseType = new ExerciseType();
        exerciseType.setId(new ExerciseTypeId());
        exerciseType.setCreatedBy(educator.getOwnerUserId());
        exerciseType.setEducator(educator);
        return exerciseType;
    }
}