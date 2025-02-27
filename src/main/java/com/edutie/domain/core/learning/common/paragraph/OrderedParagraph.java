package com.edutie.domain.core.learning.common.paragraph;

import com.edutie.domain.core.common.base.identity.Identifier;
import jakarta.persistence.MappedSuperclass;
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
@MappedSuperclass
public abstract class OrderedParagraph<TParagraphData extends Serializable, TId extends Identifier<?>> extends Paragraph<TParagraphData, TId> {
    private int ordinal = 0;
}
