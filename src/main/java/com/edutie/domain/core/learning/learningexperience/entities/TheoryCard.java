package com.edutie.domain.core.learning.learningexperience.entities;

import com.edutie.domain.core.common.base.EntityBase;
import com.edutie.domain.core.education.learningsubject.identities.LearningRequirementId;
import com.edutie.domain.core.learning.learningexperience.identities.TheoryCardId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
@Entity
public class TheoryCard extends EntityBase<TheoryCardId> {
    private LearningRequirementId learningRequirementId;
    @Column(columnDefinition = "TEXT")
    private String overview;

    public static TheoryCard create(LearningRequirementId learningRequirementId, String overviewText) {
        TheoryCard theoryCard = new TheoryCard();
        theoryCard.setId(new TheoryCardId());
        theoryCard.setLearningRequirementId(learningRequirementId);
        theoryCard.setOverview(overviewText);
        return theoryCard;
    }
}
