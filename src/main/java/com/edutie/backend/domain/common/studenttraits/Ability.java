package com.edutie.backend.domain.common.studenttraits;

import com.edutie.backend.domain.common.enums.AbstractEnumConverter;
import com.edutie.backend.domain.common.enums.PersistableEnum;
import lombok.Getter;

/**
 * Ability enum describing skills that student can have according to CHC theory
 * @see <a href="https://en.wikipedia.org/wiki/Cattell%E2%80%93Horn%E2%80%93Carroll_theory">Cattell-Horn-Carroll theory</a>
 */
//TODO: broaden/adjust Ability traits types
@Getter
public enum Ability implements PersistableEnum<String> {
    ADAPTABILITY("Adaptability"),
    ANALYTICAL_THINKING("Analytical Thinking"),
    CRITICAL_THINKING("Critical Thinking"),
    CREATIVITY("Creativity"),
    GOAL_SETTING("Goal Setting"),
    INFORMATION_SYNTHESIS("Information Synthesis"),
    METACOGNITION("Metacognition"),
    PROBLEM_SOLVING("Problem Solving"),
    READING_COMPREHENSION("Reading Comprehension"),
    RESEARCH("Research"),
    SELF_ASSESSMENT("Self Assessment"),
    VISUALIZATION("Visualization");


    private final String code;

    Ability(String code)
    {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
    public static class Converter extends AbstractEnumConverter<Ability, String> {
        public Converter() {
            super(Ability.class);
        }
    }
}

