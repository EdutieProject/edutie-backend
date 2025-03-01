package com.edutie.domain.core.common.paragraph;

import com.edutie.domain.core.common.base.identity.Identifier;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * A Paragraph but with an ordinal, knowing its order
 *
 * @param <TId> Type of paragraph id.
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@MappedSuperclass
public abstract class OrderedParagraph<TParagraphData extends Serializable, TId extends Identifier<?>> extends Paragraph<TParagraphData, TId> {
    private int ordinal = 0;
}
