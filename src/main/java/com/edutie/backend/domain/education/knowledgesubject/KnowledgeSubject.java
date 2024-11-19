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
@Getter
public class KnowledgeSubject extends EntityBase<KnowledgeSubjectId> {
    private String title;

    public static KnowledgeSubject create(
            KnowledgeSubjectId id,
            String title
    ) {
        KnowledgeSubject knowledgeSubject = new KnowledgeSubject();
        knowledgeSubject.setId(id);
        knowledgeSubject.title = title;
        return knowledgeSubject;
    }
}
