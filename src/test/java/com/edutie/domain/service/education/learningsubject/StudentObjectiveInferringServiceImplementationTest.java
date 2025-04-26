package com.edutie.domain.service.education.learningsubject;

import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.learningsubject.entities.KnowledgeOrigin;
import com.edutie.domain.core.education.learningsubject.service.StudentObjectiveInferringService;
import com.edutie.infrastructure.knowledgemap.learningsubject.StudentObjectiveGenerationService;
import com.edutie.infrastructure.knowledgemap.learningsubject.schema.StudentObjectiveGenerationSchema;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import validation.WrapperResult;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentObjectiveInferringServiceImplementationTest {

    @Test
    void getStudentObjective() {
        StudentObjectiveInferringService studentObjectiveInferringService = new StudentObjectiveInferringServiceImplementation(
                generationSchema -> WrapperResult.successWrapper(generationSchema.objectiveTitle() + " Hello World!")
        );

        WrapperResult<PromptFragment> studentObjectiveResult = studentObjectiveInferringService.getStudentObjective("Hey there!", new KnowledgeOrigin());

        assertTrue(studentObjectiveResult.isSuccess());
        PromptFragment promptFragment = studentObjectiveResult.getValue();
        assertTrue(promptFragment.equals(PromptFragment.of("Hey there! Hello World!")));
    }
}