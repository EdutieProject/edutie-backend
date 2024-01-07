package com.edutie.edutiebackend.domain.core.learningresult;

import com.edutie.edutiebackend.domain.core.common.base.AuditableEntityBase;
import com.edutie.edutiebackend.domain.core.learningresult.exceptions.InvalidSkillPointsValueException;
import com.edutie.edutiebackend.domain.core.learningresult.identities.LearningResultId;
import com.edutie.edutiebackend.domain.core.learningresult.validation.SkillPointsValidator;
import com.edutie.edutiebackend.domain.core.learningresult.valueobjects.Feedback;
import com.edutie.edutiebackend.domain.core.lessonsegment.LessonSegment;
import com.edutie.edutiebackend.domain.core.lessonsegment.entities.LearningRequirement;
import com.edutie.edutiebackend.domain.core.lessonsegment.identities.LessonSegmentId;
import com.edutie.edutiebackend.domain.core.skill.Skill;
import com.edutie.edutiebackend.domain.core.skill.identities.SkillId;
import com.edutie.edutiebackend.domain.core.student.Student;
import com.edutie.edutiebackend.domain.core.student.identities.StudentId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * A result of learning activities that is used
 * to adapt students learning parameters.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
//TODO: reevaluate responsibilities of LearningResult: think whether it is necessary to carry feedbackText and ReportText
public class LearningResult extends AuditableEntityBase<LearningResultId> {
    @ManyToOne(targetEntity = LessonSegment.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_segment_id", updatable = false, insertable = false)
    @JsonIgnore
    private LessonSegment lessonSegment;
    @Column(name = "lesson_segment_id")
    private LessonSegmentId lessonSegmentId;
    @ManyToOne(targetEntity = Student.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id",updatable = false, insertable = false)
    @JsonIgnore
    private Student student;
    @Column(name = "student_id")
    private StudentId studentId;

    private String reportText;
    @Embedded
    private Feedback feedback;
    // many-to-many relationship with additional field: pointsValue
    //TODO: decide whether we need hashmap here
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "learning_result_skill_points",
    joinColumns = {@JoinColumn(name = "skill_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "")})
    private Map<Skill, Integer> skillPoints;
    private Map<LearningRequirement, Integer> learningPoints;

    /**
     * Assigns skill points to given skill
     * @param skillId id of skill
     * @param points points to assign
     * @throws InvalidSkillPointsValueException exception thrown when points value is invalid
     * @see SkillPointsValidator Validating skill points value
     */
    public void assignSkillPoints(SkillId skillId, int points) throws InvalidSkillPointsValueException {
        if(SkillPointsValidator.isValid(points))
            skillPoints.put(skillId, points);
    }

    /**
     * removes skill from skill points mapping
     * @param skillId skill  to remove
     */
    public void removeSkillPoints(SkillId skillId)
    {
        skillPoints.remove(skillId);
    }

    /**
     * Retrieves points associated with given skill
     * @param skillId id of skill
     * @return amount of points as integer
     */
    public Integer getSkillPoints(SkillId skillId)
    {
        return skillPoints.get(skillId);
    }
}
