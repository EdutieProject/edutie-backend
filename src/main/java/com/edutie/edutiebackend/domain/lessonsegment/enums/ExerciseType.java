package com.edutie.edutiebackend.domain.lessonsegment.enums;

import lombok.Getter;

//TODO: Review: entity or enum?
@Getter
public enum ExerciseType {
    /**
     * PBL typically involves students being presented with a real-world problem or scenario without a predetermined solution.
     * Students work collaboratively to analyze the problem, identify relevant concepts and information,
     * and propose solutions or recommendations.The primary focus is on understanding the problem and its underlying concepts.
     */
    ProblemBased("Problem Based"),
    /**
     * PrBL involves students working on an extended project or task that results in a tangible product or deliverable.
     * The project often spans an extended period and can be interdisciplinary.
     * Students apply their knowledge and skills to create something concrete, such as a research report,
     * a presentation, a model, or an artifact.
     */
    ProjectBased("Project Based"),
    /**
     * CBL presents students with real or hypothetical cases, often from professional or clinical contexts,
     * and challenges them to analyze and make decisions based on the information provided.
     * This approach helps learners apply theoretical knowledge to practical situations.
     */
    CaseScenario("Case scenario"),
    /**
     * IBL is an approach where students pose questions, research, and investigate topics independently or collaboratively.
     * It encourages students to explore, analyze, and experiment to discover answers and develop problem-solving skills.
     */
    InquiryBased("Inquiry Based"),
    /**
     * Quiz-based tasks are educational activities that involve the use of quizzes to assess and reinforce learning.
     * These tasks typically include a set of questions or problems that students must answer within a specified time frame
     */
    Quiz("Quiz");

    private final String code;
    ExerciseType(String code)
    {
        this.code = code;
    }
}
