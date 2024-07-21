package com.edutie.backend.application.management.learningresourcedefinition.implementation;

import com.edutie.backend.application.management.learningresourcedefinition.CreatedLearningResourceDefinitionsQueryHandler;
import com.edutie.backend.application.management.learningresourcedefinition.queries.CreatedLearningResourceDefinitionsQuery;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.personalization.learningresourcedefinition.LearningResourceDefinition;
import com.edutie.backend.domain.personalization.learningresourcedefinition.persistence.LearningResourceDefinitionPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CreatedLearningResourceDefinitionsQueryHandlerImplementation implements CreatedLearningResourceDefinitionsQueryHandler {
    private final LearningResourceDefinitionPersistence learningResourceDefinitionPersistence;
    private final EducatorPersistence educatorPersistence;

    @Override
    public WrapperResult<List<LearningResourceDefinition>> handle(CreatedLearningResourceDefinitionsQuery command) {
        Educator educator = educatorPersistence.getByAuthorizedUserId(command.educatorUserId());
        return learningResourceDefinitionPersistence.getByAuthorEducator(educator.getId());
    }
}
