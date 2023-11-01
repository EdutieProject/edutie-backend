package com.edutie.edutiebackend.domain.lessonsegment;

import com.edutie.edutiebackend.domain.common.base.EntityBase;
import com.edutie.edutiebackend.domain.common.identities.LessonSegmentId;
import com.edutie.edutiebackend.domain.lessonsegment.valueobjects.ExternalSource;
import com.edutie.edutiebackend.domain.lessonsegment.valueobjects.GenerationPrompt;
import jakarta.persistence.Entity;

import java.util.Set;

/**
 * A wrapper around the learning resource. It indicates whether the resource is given statically
 * or generated dynamically - via the IsDynamic field.
 */
@Entity
public class LessonSegment extends EntityBase<LessonSegmentId> {
    private GenerationPrompt overviewGenerationPrompt;
    private GenerationPrompt exerciseGenerationPrompt;
    private Boolean isDynamic;
    private LessonSegmentId nextSegmentId;
    private LessonSegmentId previousSegmentId;
    public Set<ExternalSource> externalSources;

}
