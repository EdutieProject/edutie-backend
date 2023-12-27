package com.edutie.edutiebackend.domain.core.lessonsegment;

import com.edutie.edutiebackend.domain.core.common.base.AuditableEntityBase;
import com.edutie.edutiebackend.domain.core.common.generationprompt.PromptFragment;
import com.edutie.edutiebackend.domain.core.common.studynavigation.LearningTreeNavigator;
import com.edutie.edutiebackend.domain.core.lesson.identities.LessonId;
import com.edutie.edutiebackend.domain.core.lessonsegment.entities.ExerciseType;
import com.edutie.edutiebackend.domain.core.lessonsegment.identities.LessonSegmentId;
import com.edutie.edutiebackend.domain.core.lessonsegment.valueobjects.ExternalSource;
import com.edutie.edutiebackend.domain.core.skill.identities.SkillId;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


/**
 * A wrapper around the learning resource. It can manage navigation through navigation property.
 * It has all necessities to provide learning resource by generation or by selection.
 */
@NoArgsConstructor
@Getter
@EqualsAndHashCode(callSuper = true)
@Entity
public class LessonSegment extends AuditableEntityBase<LessonSegmentId> {
    @Setter
    private String name;
    // embed learning navigation
    public final LearningTreeNavigator<LessonSegmentId> navigation = new LearningTreeNavigator<>();
    // embed
    @Setter
    private PromptFragment overviewDescription;
    // embed
    @Setter
    private PromptFragment exerciseDescription;

    // many-to-many relationship
    @Setter
    private ExerciseType exerciseType;
    // one-to-many
    private final Set<ExternalSource> externalSources = new HashSet<>();

    // many-to-many
    private final Set<SkillId> skills = new HashSet<>();
    // many-to-one
    private LessonId lessonId;

    /**
     * Recommended constructor assigning the segment
     * to the given lesson
     * @param lessonId lesson id
     */
    public LessonSegment(LessonId lessonId)
    {
        this.lessonId = lessonId;
    }

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
