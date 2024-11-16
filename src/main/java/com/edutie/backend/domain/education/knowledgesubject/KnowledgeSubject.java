package com.edutie.backend.domain.education.knowledgesubject;

import com.edutie.backend.domain.common.base.EntityBase;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Knowledge subject is a wikidata knowledge node representation.
 */
public class KnowledgeSubject extends EntityBase<KnowledgeSubjectId> {
    private String title;
    private String description;

    public static KnowledgeSubject create(
            KnowledgeSubjectId id,
            String title,
            String description
    ) {
        KnowledgeSubject knowledgeSubject = new KnowledgeSubject();
        knowledgeSubject.setId(id);
        knowledgeSubject.title = title;
        knowledgeSubject.description = description;
        return knowledgeSubject;
    }
}
