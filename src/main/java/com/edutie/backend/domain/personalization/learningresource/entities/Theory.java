package com.edutie.backend.domain.personalization.learningresource.entities;

import com.edutie.backend.domain.common.base.EntityBase;
import com.edutie.backend.domain.personalization.learningresource.identities.TheoryId;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
public class Theory extends EntityBase<TheoryId> {
    public static Theory create() {
        Theory theory = new Theory();
        theory.setId(new TheoryId());
        return theory;
    }
}
