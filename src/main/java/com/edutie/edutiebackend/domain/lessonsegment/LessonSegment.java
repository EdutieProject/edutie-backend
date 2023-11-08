package com.edutie.edutiebackend.domain.lessonsegment;

import com.edutie.edutiebackend.domain.common.base.EntityBase;
import com.edutie.edutiebackend.domain.common.identities.CommonSkillId;
import com.edutie.edutiebackend.domain.common.identities.ExerciseTypeId;
import com.edutie.edutiebackend.domain.common.identities.LessonSegmentId;
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
    private ExerciseTypeId exerciseType;

    private Boolean isDynamic;
    private Set<ExternalSource> externalSources = new HashSet<>();
    private Set<CommonSkillId> commonSkills = new HashSet<>();

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
     * @param commonSkillId common skill identifier
     */
    public void addCommonSkill(CommonSkillId commonSkillId)
    {
        commonSkills.add(commonSkillId);
    }

    /**
     * Removes common skill from the common skills list
     * @param commonSkillId common skill identifier
     */
    public void removeCommonSkill(CommonSkillId commonSkillId)
    {
        commonSkills.remove(commonSkillId);
    }
}
