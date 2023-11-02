package com.edutie.edutiebackend.domain.lessonsegment;

import com.edutie.edutiebackend.domain.common.base.EntityBase;
import com.edutie.edutiebackend.domain.common.identities.LessonSegmentId;
import com.edutie.edutiebackend.domain.lessonsegment.valueobjects.ExternalSource;
import com.edutie.edutiebackend.domain.lessonsegment.valueobjects.GenerationPrompt;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * A wrapper around the learning resource. It indicates whether the resource is given statically
 * or generated dynamically - via the IsDynamic field.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class LessonSegment extends EntityBase<LessonSegmentId> {
    private GenerationPrompt overviewGenerationPrompt;
    private GenerationPrompt exerciseGenerationPrompt;
    private Boolean isDynamic;
    private LessonSegmentId nextSegmentId;
    private LessonSegmentId previousSegmentId;
    private Set<ExternalSource> externalSources;

    public void addExternalSource(ExternalSource source)
    {
        externalSources.add(source);
    }
    public void removeExternalSource(ExternalSource source)
    {
        externalSources.remove(source);
    }
}
