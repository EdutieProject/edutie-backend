package com.edutie.edutiebackend.domain.core.student.studentTraits;

/**
 * Skill enum describing skills that student can have
 */
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

    public String getCode()
    {
        return code;
    }
}
