package com.edutie.backend.mocks;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;

import java.util.UUID;

public class EducationMocks {
    public static LearningRequirement independentLearningRequirement(Educator educator) {
        LearningRequirement learningRequirement = LearningRequirement.create(educator);
        learningRequirement.setName("Integration by parts");
        learningRequirement.setKnowledgeSubjectId(new KnowledgeSubjectId());
        learningRequirement.appendSubRequirement(
                "Calculating derivatives and antiderivatives of ingredient functions",
                PromptFragment.of("")
        );
        learningRequirement.appendSubRequirement(
                "Proper formula usage",
                PromptFragment.of("")
        );
        learningRequirement.appendSubRequirement(
                "3rd sub req nfgoiufguoeoeaofsoefe",
                PromptFragment.of("")
        );
        return learningRequirement;
    }

    public static LearningRequirement relatedLearningRequirement(Educator educator) {
        LearningRequirement learningRequirement = LearningRequirement.create(educator);
        learningRequirement.setName("Integration by substitution");
        learningRequirement.setKnowledgeSubjectId(new KnowledgeSubjectId());
        learningRequirement.appendSubRequirement(
                "Substituting an expression with a variable",
                PromptFragment.of("")
        );
        learningRequirement.appendSubRequirement(
                "Correct substituted expression calculation",
                PromptFragment.of("")
        );
        learningRequirement.appendSubRequirement(
                "3rd sub req ...",
                PromptFragment.of("")
        );
        return learningRequirement;
    }
}
