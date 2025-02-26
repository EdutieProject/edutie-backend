package com.edutie.domain.core.common.base.identity;

import jakarta.persistence.MappedSuperclass;

/**
 * A base class for implementing wikidata nodes Id
 */
@MappedSuperclass
public class WikidataIdentifier extends Identifier<String> {
    public WikidataIdentifier(String value) {
        super(value);
    }
}
