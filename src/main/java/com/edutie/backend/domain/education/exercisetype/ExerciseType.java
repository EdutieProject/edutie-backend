package com.edutie.backend.domain.education.exercisetype;

import com.edutie.backend.domain.common.base.EducatorCreatedAuditableEntity;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.exercisetype.entities.ReportTemplateParagraph;
import com.edutie.backend.domain.education.exercisetype.identities.ExerciseTypeId;
import jakarta.persistence.*;
import lombok.*;
import validation.Error;
import validation.Result;

import java.util.ArrayList;
import java.util.List;

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
    @Setter(AccessLevel.PRIVATE)
    List<ReportTemplateParagraph> reportTemplate = new ArrayList<>();

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

    public Result insertReportTemplateParagraph(int desiredIndex, String title, String text) {
        appendReportTemplateParagraph(title, text);
        return moveReportTemplateParagraph(reportTemplate.size() - 1, desiredIndex);
    }

    /**
     * Appends new report template paragraph at the end of report template
     *
     * @param title title of the paragraph
     * @param text  text of the paragraph
     */
    public void appendReportTemplateParagraph(String title, String text) {
        ReportTemplateParagraph newParagraph = ReportTemplateParagraph.create(title, text);
        newParagraph.setOrdinal(reportTemplate.size());
        reportTemplate.add(newParagraph);
    }

    /**
     * Moves report template paragraph & restructures the paragraph order.
     *
     * @param paragraphIndex index of a selected paragraph to be moved
     * @param newIndex       desired index of a paragraph
     * @return Result object
     */
    public Result moveReportTemplateParagraph(int paragraphIndex, int newIndex) {
        if (paragraphIndex < 0 || newIndex < 0 || paragraphIndex > reportTemplate.size() - 1 || newIndex > reportTemplate.size() - 1)
            //TODO!
            return Result.failure(new Error("TODO!", this.getClass().getSimpleName()));
        ReportTemplateParagraph paragraphToMove = reportTemplate.get(paragraphIndex);
        reportTemplate.remove(paragraphToMove);
        reportTemplate.add(newIndex, paragraphToMove);
        for (int i = 0; i < reportTemplate.size(); i++) {
            reportTemplate.get(i).setOrdinal(i);
        }
        return Result.success();
    }

    /**
     * Removes report template paragraph
     *
     * @param paragraphIndex index of paragraph to be removed
     * @return Result object
     */
    public Result removeReportTemplateParagraph(int paragraphIndex) {
        if (reportTemplate.remove(paragraphIndex) == null)
            return Result.failure(new Error("TODO!", this.getClass().getSimpleName()));
        for (int i = paragraphIndex; i < reportTemplate.size(); i++) {
            reportTemplate.get(i).setOrdinal(i);
        }
        return Result.success();
    }
}