package com.edutie.edutiebackend.domain.learningresource;

import com.edutie.edutiebackend.domain.common.EntityBase;
import com.edutie.edutiebackend.domain.learningresource.interfaces.ILearningActivity;
import com.edutie.edutiebackend.domain.learningresource.valueobjects.KnowledgeSource;
import com.edutie.edutiebackend.domain.learningresource.valueobjects.Overview;
import jakarta.persistence.Entity;

import java.util.Set;

/**
 * A singular form of learning in the application.
 * This is the resource which is used by the learner to exercise
 * his knowledge.
 */
@Entity
public class LearningResource extends EntityBase<LearningResource> {
    private String name;
    public Overview overview;
    public Set<KnowledgeSource> sources;
    public ILearningActivity learningActivity;
}
