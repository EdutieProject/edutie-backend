package com.edutie.backend.domain.personalization.learningresourcedefinition.entities;

import com.edutie.backend.domain.common.base.EntityBase;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.TheoryDetailsId;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class TheoryDetails extends EntityBase<TheoryDetailsId> {
    @AttributeOverride(name = "text", column = @Column(name = "overview_description", columnDefinition = "TEXT"))
    private PromptFragment overviewDescription;
    @AttributeOverride(name = "text", column = @Column(name = "graph_description", columnDefinition = "TEXT"))
    private PromptFragment graphDescription;

    public static TheoryDetails create(PromptFragment overviewDescription, PromptFragment graphDescription) {
        TheoryDetails theoryDetails = new TheoryDetails();
        theoryDetails.setId(new TheoryDetailsId());
        theoryDetails.setOverviewDescription(overviewDescription);
        theoryDetails.setGraphDescription(graphDescription);
        return theoryDetails;
    }
}
