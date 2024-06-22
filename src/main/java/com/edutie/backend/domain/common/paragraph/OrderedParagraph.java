package com.edutie.backend.domain.common.paragraph;

import com.edutie.backend.domain.common.base.identity.Identifier;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "location")
public abstract class OrderedParagraph<TId extends Identifier<?>> extends Paragraph<TId> {
    private short ordinal = 0;
}
