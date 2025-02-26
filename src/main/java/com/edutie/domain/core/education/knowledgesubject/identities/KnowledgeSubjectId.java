package com.edutie.domain.core.education.knowledgesubject.identities;

import com.edutie.domain.core.common.base.identity.WikidataIdentifier;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.Embeddable;

@Embeddable
public class KnowledgeSubjectId extends WikidataIdentifier {
    public KnowledgeSubjectId() {
        super("Q" + Integer.valueOf((int) Math.floor(Math.random() * 1000)).toString());
    }

    @JsonCreator
    public KnowledgeSubjectId(String wikidataId) {
        super(wikidataId);
    }
}
