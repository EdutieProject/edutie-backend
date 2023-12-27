package com.edutie.edutiebackend.domain.core.common.studenttraits;

import lombok.Getter;

/**
 * Ability enum describing skills that student can have according to CHC theory
 * @see <a href="https://en.wikipedia.org/wiki/Cattell%E2%80%93Horn%E2%80%93Carroll_theory">Cattell-Horn-Carroll theory</a>
 */
//TODO: broaden/adjust Ability traits types
@Getter
public enum Ability {
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
}
