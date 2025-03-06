package com.edutie.domain.core.education.knowledgesubject;

import com.edutie.domain.core.common.base.EntityBase;
import com.edutie.domain.core.education.knowledgesubject.identities.KnowledgeSubjectId;
import lombok.Getter;

/**
 * Knowledge subject is a wikidata knowledge node representation.
 */
@Getter
public class KnowledgeSubject extends EntityBase<KnowledgeSubjectId> {
    private String title;

    public static KnowledgeSubject create(KnowledgeSubjectId id) {
        KnowledgeSubject knowledgeSubject = new KnowledgeSubject();
        knowledgeSubject.setId(id);
        return knowledgeSubject;
    }

    public static KnowledgeSubject create(KnowledgeSubjectId id, String title) {
        KnowledgeSubject knowledgeSubject = create(id);
        knowledgeSubject.title = title;
        return knowledgeSubject;
    }
}
