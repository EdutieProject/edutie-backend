package com.edutie.edutiebackend.domain.lessonsegment;

import com.edutie.edutiebackend.domain.common.base.EntityBase;
import com.edutie.edutiebackend.domain.lessonsegment.entities.ExerciseType;
import com.edutie.edutiebackend.domain.skill.identities.SkillId;
import com.edutie.edutiebackend.domain.lessonsegment.identities.LessonSegmentId;
import com.edutie.edutiebackend.domain.common.studynavigation.LearningTreeNavigator;
import com.edutie.edutiebackend.domain.lessonsegment.valueobjects.ExternalSource;
import com.edutie.edutiebackend.domain.lessonsegment.valueobjects.GenerationPrompt;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

/**
 * A wrapper around the learning resource. It can manage navigation through navigation property.
 * It has all necessities to provide learning resource by generation or by selection.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class LessonSegment extends EntityBase<LessonSegmentId> {

    private LearningTreeNavigator<LessonSegmentId> navigation;

    private GenerationPrompt overviewGenerationPrompt;

    private GenerationPrompt exerciseGenerationPrompt;
    private ExerciseType exerciseType;

    private Set<ExternalSource> externalSources = new HashSet<>();
    private Set<SkillId> skills = new HashSet<>();

    /**
     * Adds external source to the lesson segment
     * @param source source to be added
     */
    public void addExternalSource(ExternalSource source)
    {
        externalSources.add(source);
    }

    /**
     * Removes external source from the set of external sources.
     * @param source source to be removed
     */
    public void removeExternalSource(ExternalSource source)
    {
        externalSources.remove(source);
    }

    /**
     * Adds common skill to the common skills list
     * @param skillId skill identifier
     */
    public void addSkill(SkillId skillId)
    {
        skills.add(skillId);
    }

    /**
     * Removes common skill from the common skills list
     * @param skillId skill identifier
     */
    public void removeSkill(SkillId skillId)
    {
        skills.remove(skillId);
    }
}
