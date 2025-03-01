package com.edutie.domain.core.education.learningsubject.entities;

import com.edutie.domain.core.education.knowledgesubject.identities.KnowledgeSubjectId;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class KnowledgeOrigin {
    @Embedded
    @AttributeOverride(name = "identifierValue", column = @Column(name = "knowledge_subject_id"))
    private KnowledgeSubjectId knowledgeSubjectId;

    public boolean isEmpty() {
        return knowledgeSubjectId == null;
    }
}
