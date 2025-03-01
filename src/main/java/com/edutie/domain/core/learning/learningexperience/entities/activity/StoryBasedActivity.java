package com.edutie.domain.core.learning.learningexperience.entities.activity;

import com.edutie.domain.core.learning.learningexperience.entities.activity.base.ActivityBase;
import com.edutie.domain.core.learning.learningexperience.entities.activity.common.ActivityType;
import com.edutie.domain.core.learning.learningexperience.entities.activity.common.AnswerOption;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.net.URL;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"answerOptionOne", "answerOptionTwo", "answerOptionThree", "answerOptionFour"})
public class StoryBasedActivity extends ActivityBase {
    private String problemText;
    private URL imageURL;
    private String continuousQuestion;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "text", column = @Column(name = "answer_option_1")),
            @AttributeOverride(name = "isCorrect", column = @Column(name = "answer_option_1_correct"))
    })
    private AnswerOption answerOptionOne;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "text", column = @Column(name = "answer_option_2")),
            @AttributeOverride(name = "isCorrect", column = @Column(name = "answer_option_2_correct"))
    })
    private AnswerOption answerOptionTwo;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "text", column = @Column(name = "answer_option_3")),
            @AttributeOverride(name = "isCorrect", column = @Column(name = "answer_option_3_correct"))
    })
    private AnswerOption answerOptionThree;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "text", column = @Column(name = "answer_option_4")),
            @AttributeOverride(name = "isCorrect", column = @Column(name = "answer_option_4_correct"))
    })
    private AnswerOption answerOptionFour;

    @JsonProperty
    public Set<AnswerOption> getAnswerOptions() {
        return Set.of(answerOptionOne, answerOptionTwo, answerOptionThree, answerOptionFour);
    }

    @Override
    public ActivityType getActivityType() {
        return ActivityType.UNDERSTANDING_ACTIVITY;
    }
}
