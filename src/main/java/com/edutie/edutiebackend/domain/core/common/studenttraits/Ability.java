package com.edutie.edutiebackend.domain.core.common.studenttraits;

import lombok.Getter;

/**
 * Ability enum describing skills that student can have
 */
@Getter
public enum Ability {
    Adaptability("Adaptability"),
    AnalitycalThinking("Analitycal Thinking"),
    CriticalThinking("Critical Thinking"),
    Creativity("Creativity"),
    GoalSetting("Goal Setting"),
    InformationSynthesis("Information Synthesis"),
    Metacognition("Metacognition"),
    ProblemSolving("Problem Solving"),
    ReadingComprehension("Reading Comprehension"),
    Research("Research"),
    SelfAssessment("Self Assessment"),
    Visualization("Visualization");


    private final String code;

    Ability(String code)
    {
        this.code = code;
    }

}
