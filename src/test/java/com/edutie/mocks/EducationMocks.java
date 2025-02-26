package com.edutie.mocks;

import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.educator.Educator;
import com.edutie.domain.core.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.domain.core.education.learningsubject.LearningSubject;

public class EducationMocks {
    public static LearningSubject independentLearningRequirement(Educator educator) {
        LearningSubject learningSubject = LearningSubject.createBlank(educator);
        learningSubject.setName("Integration by parts");
        learningSubject.setKnowledgeSubjectId(new KnowledgeSubjectId());
        learningSubject.appendSubRequirement(
                "Calculating derivatives and antiderivatives of ingredient functions",
                PromptFragment.of("")
        );
        learningSubject.appendSubRequirement(
                "Proper formula usage",
                PromptFragment.of("")
        );
        learningSubject.appendSubRequirement(
                "3rd sub req nfgoiufguoeoeaofsoefe",
                PromptFragment.of("")
        );
        return learningSubject;
    }

    public static LearningSubject relatedLearningRequirement(Educator educator) {
        LearningSubject learningSubject = LearningSubject.createBlank(educator);
        learningSubject.setName("Integration by substitution");
        learningSubject.setKnowledgeSubjectId(new KnowledgeSubjectId());
        learningSubject.appendSubRequirement(
                "Substituting an expression with a variable",
                PromptFragment.of("")
        );
        learningSubject.appendSubRequirement(
                "Correct substituted expression calculation",
                PromptFragment.of("")
        );
        learningSubject.appendSubRequirement(
                "3rd sub req ...",
                PromptFragment.of("")
        );
        return learningSubject;
    }
}
