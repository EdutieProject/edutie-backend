package com.edutie.domain.core.education.knowledgesubject;

import com.edutie.domain.core.common.base.EntityBase;
import com.edutie.domain.core.education.knowledgesubject.identities.KnowledgeSubjectId;
import lombok.Getter;

/**
 * Knowledge subject is a wikidata knowledge node representation.
 */
@Getter
public class KnowledgeSubjectReference extends EntityBase<KnowledgeSubjectId> {

    public static KnowledgeSubjectReference create(KnowledgeSubjectId id) {
        KnowledgeSubjectReference knowledgeSubject = new KnowledgeSubjectReference();
        knowledgeSubject.setId(id);
        return knowledgeSubject;
    }
}
