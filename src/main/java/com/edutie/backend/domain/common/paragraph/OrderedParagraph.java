package com.edutie.backend.domain.common.paragraph;

import com.edutie.backend.domain.common.base.identity.Identifier;
import jakarta.persistence.*;
import lombok.*;

/**
 * A Paragraph but with an ordinal, knowing its order
 *
 * @param <TId> Type of paragraph id.
 */
@Getter
@Setter
@MappedSuperclass
public abstract class OrderedParagraph<TId extends Identifier<?>> extends Paragraph<TId> {
	private int ordinal = 0;
}
