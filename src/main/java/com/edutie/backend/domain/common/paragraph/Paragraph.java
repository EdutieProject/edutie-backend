package com.edutie.backend.domain.common.paragraph;

import com.edutie.backend.domain.common.base.EntityBase;
import com.edutie.backend.domain.common.base.identity.Identifier;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Paragraph abstract class to be used when dealing with paragraphs. Should be extended e.g.: BookParagraph class
 *
 * @param <TId> Type of paragraph Identifier
 */
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class Paragraph<TId extends Identifier<?>> extends EntityBase<TId> {
    private String title;
    private String text;
}
