package com.edutie.domain.core.common.paragraph;

import com.edutie.domain.core.common.base.EntityBase;
import com.edutie.domain.core.common.base.identity.Identifier;
import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Abstract paragraph class
 *
 * @param <TParagraphData> type of paragraph data
 * @param <TId>            type of paragraph entity id
 */
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class Paragraph<TParagraphData extends Serializable, TId extends Identifier<?>> extends EntityBase<TId> {
    @Embedded
    private TParagraphData content;
}
