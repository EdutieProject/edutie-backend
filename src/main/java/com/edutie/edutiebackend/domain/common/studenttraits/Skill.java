package com.edutie.edutiebackend.domain.common.studenttraits;

import lombok.Getter;

/**
 * Skill enum describing skills that student can have
 */
@Getter
public enum Skill {
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

    Skill(String code)
    {
        this.code = code;
    }

}
