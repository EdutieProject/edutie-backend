package com.edutie.edutiebackend.domain.lessonsegment;

import com.edutie.edutiebackend.domain.common.base.EntityBase;
import com.edutie.edutiebackend.domain.common.identities.LessonSegmentId;
import com.edutie.edutiebackend.domain.common.studynavigation.StudyNavigation;
import com.edutie.edutiebackend.domain.lessonsegment.valueobjects.ExternalSource;
import com.edutie.edutiebackend.domain.lessonsegment.valueobjects.GenerationPrompt;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * A wrapper around the learning resource. It can manage navigation through navigation property.
 * It has all necessities to provide learning resource by generation or by selection.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class LessonSegment extends EntityBase<LessonSegmentId> {

    private StudyNavigation<LessonSegmentId> navigation;

    private GenerationPrompt overviewGenerationPrompt;
    private GenerationPrompt exerciseGenerationPrompt;
    private Boolean isDynamic;
    private Set<ExternalSource> externalSources;

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
}
