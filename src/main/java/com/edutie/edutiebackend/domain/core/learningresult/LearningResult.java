package com.edutie.edutiebackend.domain.core.learningresult;

import com.edutie.edutiebackend.domain.core.common.Utilities;
import com.edutie.edutiebackend.domain.core.common.base.AuditableEntityBase;
import com.edutie.edutiebackend.domain.core.learningresult.entities.LearningAssessment;
import com.edutie.edutiebackend.domain.core.learningresult.entities.SkillAssessment;
import com.edutie.edutiebackend.domain.core.learningresult.entities.base.Assessment;
import com.edutie.edutiebackend.domain.core.learningresult.errors.LearningResultErrors;
import com.edutie.edutiebackend.domain.core.learningresult.identities.LearningResultId;
import com.edutie.edutiebackend.domain.core.learningresult.rules.AssessmentPointsBoundsRule;
import com.edutie.edutiebackend.domain.core.learningresult.valueobjects.Feedback;
import com.edutie.edutiebackend.domain.core.lessonsegment.LessonSegment;
import com.edutie.edutiebackend.domain.core.learningrequirement.LearningRequirement;
import com.edutie.edutiebackend.domain.core.skill.Skill;
import com.edutie.edutiebackend.domain.core.student.Student;
import com.edutie.edutiebackend.domain.rule.Error;
import com.edutie.edutiebackend.domain.rule.Result;
import com.edutie.edutiebackend.domain.rule.Rule;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * A result of learning activities that is used
 * to adapt students learning parameters. Even though it is
 * adjusted for modifications, it should stay immutable and be
 * used only as a learning history fragment.
 */
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
//TODO: add javadoc
public class LearningResult extends AuditableEntityBase<LearningResultId> {
    @ManyToOne(targetEntity = LessonSegment.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "lesson_segment_id")
    @Setter
    @JsonIgnore
    private LessonSegment lessonSegment;
    @ManyToOne(targetEntity = Student.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Student student;
    @Setter
    private String reportText;
    @Setter
    @Embedded
    private Feedback feedback = new Feedback();
    @OneToMany(targetEntity = SkillAssessment.class)
    private final Set<SkillAssessment> skillAssessments = new HashSet<>();
    @OneToMany(targetEntity = LearningAssessment.class)
    private final Set<LearningAssessment> learningAssessments = new HashSet<>();

    public LearningResult(LessonSegment lessonSegment, Student student) {
        this.lessonSegment = lessonSegment;
        this.student = student;
    }

    public Set<Assessment<?>> getAllAssessments() {
        Set<Assessment<?>> assessments = new HashSet<>();
        assessments.addAll(skillAssessments);
        assessments.addAll(learningAssessments);
        return assessments;
    }

    public <T extends Assessment<?>> Result addAssessment(T assessment, Class<T> assessmentClass)
    {
        var assessmentSet = Utilities.findSetOf(assessmentClass, this).orElseThrow();
        var validationResult = Rule.validate(AssessmentPointsBoundsRule.class, assessment.getAssessmentPoints());
        if(validationResult.isFailure())
            return validationResult;
        var searchedAssessment =  assessmentSet.stream().filter(o->o.getEntity() == assessment.getEntity()).findFirst();
        searchedAssessment.ifPresent(assessmentSet::remove);
        assessmentSet.add(assessment);
        return validationResult;
    }

    public <T, U extends Assessment<T>> Result addAssessment(T assessedEntity, int assessmentPoints, Class<U> assessmentClass) {
        return Result.failure(new Error("",""));
    }

    public Result removeAssessment(Skill skill) {
        var searchedAssessment = skillAssessments.stream().filter(o -> o.getEntity() == skill).findFirst();
        searchedAssessment.ifPresent(skillAssessments::remove);
        return searchedAssessment.isPresent() ? Result.success() : Result.failure(LearningResultErrors.noAssessmentFound());
    }

    public Result removeAssessment(LearningRequirement learningRequirement) {
        var searchedAssessment = learningAssessments.stream().filter(o -> o.getEntity() == learningRequirement).findFirst();
        searchedAssessment.ifPresent(learningAssessments::remove);
        return searchedAssessment.isPresent() ? Result.success() : Result.failure(LearningResultErrors.noAssessmentFound());
    }


}
