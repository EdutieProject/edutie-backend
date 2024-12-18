package com.edutie.backend.domain.personalization.learningresource.entities;

import com.edutie.backend.domain.common.base.EntityBase;
import com.edutie.backend.domain.personalization.learningresource.identities.HintId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Entity
public class Hint extends EntityBase<HintId> {
    @Setter(AccessLevel.PROTECTED)
    @Column(columnDefinition = "TEXT")
    private String text;

    public static Hint create(String text) {
        Hint hint = new Hint();
        hint.setId(new HintId());
        hint.setText(text);
        return hint;
    }
}
