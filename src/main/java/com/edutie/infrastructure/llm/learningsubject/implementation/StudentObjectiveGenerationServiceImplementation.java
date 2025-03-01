package com.edutie.infrastructure.llm.learningsubject.implementation;

import com.edutie.infrastructure.llm.learningsubject.StudentObjectiveGenerationService;
import com.edutie.infrastructure.llm.learningsubject.schema.StudentObjectiveGenerationSchema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Slf4j
@RequiredArgsConstructor
@Component
public class StudentObjectiveGenerationServiceImplementation implements StudentObjectiveGenerationService {
    @Override
    public WrapperResult<String> generate(StudentObjectiveGenerationSchema generationSchema) {
        log.info("Generating student objective for {}", generationSchema);
        //TODO!!!
        return WrapperResult.successWrapper("DUPA");
    }
}
