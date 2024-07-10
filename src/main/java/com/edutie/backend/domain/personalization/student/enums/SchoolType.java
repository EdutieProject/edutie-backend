package com.edutie.backend.domain.personalization.student.enums;

import com.edutie.backend.domain.common.enums.AbstractEnumConverter;
import com.edutie.backend.domain.common.enums.PersistableEnum;
import lombok.Getter;

/**
 * Enum describing student's school type.
 */
@Getter
public enum SchoolType implements PersistableEnum<String>
{
    HIGH_SCHOOL("High School"),
    TECHNICAL_HIGH_SCHOOL("Technical High School"),
    TERTIARY_SCHOOL("Tertiary School"),
    HOMESCHOOLING("Homeschooling"),
    NO_SCHOOL("No School"),
    UNASSIGNED("Unassigned");


    private final String code;

    SchoolType(String code)
    {
        this.code = code;
    }
    public static class Converter extends AbstractEnumConverter<SchoolType, String> {
        public Converter() {
            super(SchoolType.class);
        }
    }
}
