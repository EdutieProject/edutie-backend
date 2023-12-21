package com.edutie.edutiebackend.domain.core.common.studenttraits;

import lombok.Getter;

/**
 * Ability enum describing skills that student can have
 */
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

//Po mojemu ta klasa jest zbędna. Mianowicie pod kątem oprogramowania i tworzenia może być przydatna jako całość, jednak w odniesieniu do tego co chcesz zrobić może okazać się, 
//że przynosi ona dodatkowe informacje, które są zbędne i przez to wszystko dodatkowo się komplikuje.