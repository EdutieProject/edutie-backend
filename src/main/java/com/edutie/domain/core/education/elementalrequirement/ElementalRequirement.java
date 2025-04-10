package com.edutie.domain.core.education.elementalrequirement;

import com.edutie.domain.core.common.base.EntityBase;
import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.KnowledgeProvider;
import com.edutie.domain.core.education.elementalrequirement.identitites.ElementalRequirementId;
import com.edutie.domain.core.education.learningsubject.entities.KnowledgeOrigin;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@MappedSuperclass
public abstract class ElementalRequirement<TKnowledgeProvider extends KnowledgeProvider<KnowledgeOrigin>> extends EntityBase<ElementalRequirementId> {
    protected String title;
    @Embedded
    @AttributeOverride(name = "text", column = @Column(name = "student_objective", columnDefinition = "TEXT"))
    protected PromptFragment studentObjective;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    protected TKnowledgeProvider knowledgeProvider;
}
