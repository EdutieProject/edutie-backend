package com.edutie.edutiebackend.domain.core.common.studenttraits;

import com.edutie.edutiebackend.domain.core.common.enums.AbstractEnumConverter;
import com.edutie.edutiebackend.domain.core.common.enums.PersistableEnum;
import lombok.Getter;

/**
 * Intelligence enum describing intelligence types according to 8 intelligence theory
 * @see <a href="https://en.wikipedia.org/wiki/Theory_of_multiple_intelligences">Theory of multiple intelligences</a>
 */
@Getter
public enum Intelligence implements PersistableEnum<String> {
    VISUAL("Visual"),
    LINGUISTIC("Linguistic"),
    INTERPERSONAL("Interpersonal"),
    INTRAPERSONAL("Intrapersonal"),
    LOGICAL("Logical"),
    MUSICAL("Musical"),
    KINESTHETIC("Kinesthetic"),
    NATURALISTIC("Naturalistic");

    private final String code;

    Intelligence(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }


    public static class Converter extends AbstractEnumConverter<Intelligence, String> {
        public Converter() {
            super(Intelligence.class);
        }

    }
}