package com.edutie.edutiebackend.domain.core.learningresult;

import com.edutie.edutiebackend.domain.core.shared.base.AuditableEntityBase;
import com.edutie.edutiebackend.domain.core.learningresult.entities.LearningAssessment;
import com.edutie.edutiebackend.domain.core.learningresult.entities.SkillAssessment;
import com.edutie.edutiebackend.domain.core.learningresult.errors.LearningResultErrors;
import com.edutie.edutiebackend.domain.core.learningresult.identities.LearningResultId;
import com.edutie.edutiebackend.domain.core.learningresult.rules.AssessmentPointsBoundsRule;
import com.edutie.edutiebackend.domain.core.learningresult.valueobjects.Feedback;
import com.edutie.edutiebackend.domain.core.lessonsegment.LessonSegment;
import com.edutie.edutiebackend.domain.core.lessonsegment.identities.LearningRequirementId;
import com.edutie.edutiebackend.domain.core.lessonsegment.identities.LessonSegmentId;
import com.edutie.edutiebackend.domain.core.skill.identities.SkillId;
import com.edutie.edutiebackend.domain.core.student.Student;
import com.edutie.edutiebackend.domain.core.student.identities.StudentId;
import com.edutie.edutiebackend.domain.rule.Result;
import com.edutie.edutiebackend.domain.rule.Rule;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * A result of learning activities that is used
 * to adapt students learning parameters.
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class LearningResult extends AuditableEntityBase<LearningResultId> {
    @ManyToOne(targetEntity = LessonSegment.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "lesson_segment_id", updatable = false, insertable = false)
    @JsonIgnore
    private LessonSegment lessonSegment;
    @Setter
    @Column(name = "lesson_segment_id")
    private LessonSegmentId lessonSegmentId;
    @ManyToOne(targetEntity = Student.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", updatable = false, insertable = false)
    @JsonIgnore
    private Student student;
    @Setter
    @Column(name = "student_id")
    private StudentId studentId;

    @Setter
    private String reportText;
    @Setter
    @Embedded
    private Feedback feedback;
    @OneToMany
    private Set<SkillAssessment> skillAssessments = new HashSet<>();
    @OneToMany
    private Set<LearningAssessment> learningAssessments = new HashSet<>();

    public Result addSkillAssessment(SkillId skillId, int pointsValue) {
        var validationResult = Rule.validate(AssessmentPointsBoundsRule.class, pointsValue);
        if (validationResult.isSuccess())
            skillAssessments.add(SkillAssessment.create(skillId, pointsValue));
        return validationResult;
    }

    public Result removeSkillAssessment(SkillId skillId) {
        var skillAssessment = skillAssessments.stream().filter(o -> o.getSkillId() == skillId).findFirst();
        skillAssessment.ifPresent(assessment -> skillAssessments.remove(assessment));
        return skillAssessment.isPresent() ? Result.success() : Result.failure(LearningResultErrors.noAssessmentFound());
    }

    public Result addLearningAssessment(LearningRequirementId learningAssessmentId, int pointsValue) {
        var validationResult = Rule.validate(AssessmentPointsBoundsRule.class, pointsValue);
        if(validationResult.isSuccess())
            learningAssessments.add(LearningAssessment.create(learningAssessmentId, pointsValue));
        return validationResult;
    }

    public Result removeLearningAssessment(LearningRequirementId learningRequirementId) {
        var learningAssessment = learningAssessments
                .stream()
                .filter(o->o.getLearningRequirementId() == learningRequirementId)
                .findFirst();
        learningAssessment.ifPresent(assessment -> learningAssessments.remove(assessment));
        return learningAssessment.isPresent() ?
                Result.success() : Result.failure(LearningResultErrors.noAssessmentFound());
    }

}
