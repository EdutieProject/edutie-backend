package com.edutie.backend.domain.common.paragraph;

import com.edutie.backend.domain.common.base.identity.Identifier;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class OrderedParagraph<TId extends Identifier<?>> extends Paragraph<TId> {
    private int ordinal = 0;
}
