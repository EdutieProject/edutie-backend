package com.edutie.mocks.persistence.learningsubject;

import com.edutie.TestUtils;
import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.elementalrequirement.identitites.ElementalRequirementId;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.identities.LearningSubjectId;
import com.edutie.mocks.persistence.learningsubject.base.MockLearningSubjectPersistenceBase;
import validation.WrapperResult;

public class PersonalizeLearningResultLearningSubjectPersistence extends MockLearningSubjectPersistenceBase {

    private LearningSubject learningSubject;

    private void initLearningSubject() {
        try {
            learningSubject = new LearningSubject();
            learningSubject.appendRequirement("Hey", PromptFragment.empty());
            learningSubject.appendRequirement("Hey2", PromptFragment.empty());
            TestUtils.setPrivateField(learningSubject, "id", new LearningSubjectId());
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Retrieves learning subject that has an elemental requirement of provided id.
     *
     * @param elementalRequirementId elemental requirement id
     * @return Wrapper Result of Learning Subject
     */
    @Override
    public WrapperResult<LearningSubject> getLearningSubjectByElementalRequirementId(ElementalRequirementId elementalRequirementId) {
        try {
            if (learningSubject == null) {
                initLearningSubject();
            }
            return WrapperResult.successWrapper(learningSubject);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
