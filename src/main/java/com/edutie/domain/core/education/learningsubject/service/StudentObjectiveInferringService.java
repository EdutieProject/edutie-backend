package com.edutie.domain.core.education.learningsubject.service;

import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.learningsubject.entities.KnowledgeOrigin;
import validation.WrapperResult;

public interface StudentObjectiveInferringService {
    WrapperResult<PromptFragment> getStudentObjective(String title, KnowledgeOrigin knowledgeOrigin);
}
