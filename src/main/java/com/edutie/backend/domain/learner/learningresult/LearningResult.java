package com.edutie.backend.domain.learner.learningresult;

import com.edutie.backend.domain.common.Utilities;
import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.learner.learningresult.identities.AssessmentId;
import com.edutie.backend.domain.studyprogram.lessonsegment.LessonSegment;
import com.edutie.backend.domain.psychology.skill.Skill;
import com.edutie.backend.domain.learner.student.Student;
import com.edutie.backend.domain.learner.learningresult.entities.LearningAssessment;
import com.edutie.backend.domain.learner.learningresult.entities.SkillAssessment;
import com.edutie.backend.domain.learner.learningresult.entities.base.Assessment;
import com.edutie.backend.domain.learner.learningresult.errors.LearningResultErrors;
import com.edutie.backend.domain.learner.learningresult.identities.LearningResultId;
import com.edutie.backend.domain.learner.learningresult.rules.AssessmentPointsBoundsRule;
import com.edutie.backend.domain.learner.learningresult.valueobjects.Feedback;
import com.edutie.backend.domain.studyprogram.learningrequirement.LearningRequirement;
import lombok.*;
import validation.Error;
import validation.Result;
import validation.RuleEnforcer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * A result of learning activities that is used
 * to adapt students learning parameters. Even though it is
 * adjusted for modifications, it should stay immutable and be
 * used only as a learning history fragment.
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
//TODO: add javadoc
public class LearningResult extends AuditableEntityBase<LearningResultId> {
    @ManyToOne(targetEntity = LessonSegment.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "lesson_segment_id")
    @Setter(AccessLevel.PRIVATE)
    @JsonIgnore
    private LessonSegment lessonSegment;
    @Setter(AccessLevel.PRIVATE)
    @ManyToOne(targetEntity = Student.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Student student;
    private String reportText;
    @Embedded
    private Feedback feedback = new Feedback();
    @OneToMany(targetEntity = SkillAssessment.class)
    private final Set<SkillAssessment> skillAssessments = new HashSet<>();
    @OneToMany(targetEntity = LearningAssessment.class)
    private final Set<LearningAssessment> learningAssessments = new HashSet<>();

    public static LearningResult create(Student student, LessonSegment lessonSegment) {
        LearningResult learningResult = new LearningResult();
        learningResult.setId(new LearningResultId());
        learningResult.setCreatedBy(student.getCreatedBy());
        learningResult.setStudent(student);
        learningResult.setLessonSegment(lessonSegment);
        return learningResult;
    }

    public Set<Assessment<?>> getAllAssessments() {
        Set<Assessment<?>> assessments = new HashSet<>();
        assessments.addAll(skillAssessments);
        assessments.addAll(learningAssessments);
        return assessments;
    }

    public <T extends Assessment<?>> Result addAssessment(T assessment, Class<T> assessmentClass)
    {
        var validationResult = RuleEnforcer.validate(AssessmentPointsBoundsRule.class, assessment.getAssessmentPoints());
        if(validationResult.isFailure())
            return validationResult;
        var assessmentSet = Utilities.findSetOf(assessmentClass, this).orElseThrow();
        var searchedAssessment =  assessmentSet.stream().filter(o->o.getEntity() == assessment.getEntity()).findFirst();
        searchedAssessment.ifPresent(assessmentSet::remove);
        assessmentSet.add(assessment);
        return validationResult;
    }

    @SneakyThrows
    public <T, U extends Assessment<T>> Result addAssessment(T assessedEntity, int assessmentPoints, Class<U> assessmentClass) {
        var newAssessment = assessmentClass.getConstructor().newInstance();
        newAssessment.setAssessmentPoints(assessmentPoints);
        newAssessment.setEntity(assessedEntity);
        newAssessment.setId(new AssessmentId());
        return addAssessment(newAssessment, assessmentClass);
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
