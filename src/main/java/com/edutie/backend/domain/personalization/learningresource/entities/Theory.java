package com.edutie.backend.domain.personalization.learningresource.entities;

import com.edutie.backend.domain.common.base.EntityBase;
import com.edutie.backend.domain.personalization.learningresource.identities.TheoryId;
import jakarta.persistence.Entity;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@Entity
public class Theory extends EntityBase<TheoryId> {
    private String overview;
    private String summary;

    public static Theory create(String overviewText, String summaryText) {
        Theory theory = new Theory();
        theory.setId(new TheoryId());
        theory.setSummary(summaryText);
        theory.setOverview(overviewText);
        return theory;
    }
}
