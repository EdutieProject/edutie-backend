package com.edutie.infrastructure.knowledgemap.learningsubject.implementation;

import com.edutie.infrastructure.knowledgemap.learningsubject.StudentObjectiveGenerationService;
import com.edutie.infrastructure.knowledgemap.learningsubject.schema.StudentObjectiveGenerationSchema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Slf4j
@RequiredArgsConstructor
@Component
public class StudentObjectiveGenerationServiceImplementation implements StudentObjectiveGenerationService {
    @Value("${knowledge-map-url}")
    private String KNOWLEDGE_MAP_URL;

    @Override
    public WrapperResult<String> generateContext(StudentObjectiveGenerationSchema generationSchema) {
        log.info("Generating student objective for {}", generationSchema);
        //TODO!!!
        return WrapperResult.successWrapper("DUPA");
    }
}
