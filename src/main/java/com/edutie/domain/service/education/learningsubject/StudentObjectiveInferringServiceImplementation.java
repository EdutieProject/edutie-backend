package com.edutie.domain.service.education.learningsubject;

import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.learningsubject.entities.KnowledgeOrigin;
import com.edutie.domain.core.education.learningsubject.service.StudentObjectiveInferringService;
import com.edutie.infrastructure.llm.learningsubject.StudentObjectiveGenerationService;
import com.edutie.infrastructure.llm.learningsubject.schema.StudentObjectiveGenerationSchema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Slf4j
@RequiredArgsConstructor
@Component
public class StudentObjectiveInferringServiceImplementation implements StudentObjectiveInferringService {
    private final StudentObjectiveGenerationService studentObjectiveGenerationService;

    @Override
    public WrapperResult<PromptFragment> getStudentObjective(String title, KnowledgeOrigin knowledgeOrigin) {
        log.info("Inferring student objective using title {}", title);
        StudentObjectiveGenerationSchema schema = new StudentObjectiveGenerationSchema(title, knowledgeOrigin);
        return studentObjectiveGenerationService.generate(schema).map(o -> new PromptFragment(o));
    }
}
