package com.edutie.infrastructure.knowledgemap.learningsubject.implementation;

import com.edutie.infrastructure.common.ExternalInfrastructureHandler;
import com.edutie.infrastructure.common.ExternalService;
import com.edutie.infrastructure.knowledgemap.learningsubject.StudentObjectiveGenerationService;
import com.edutie.infrastructure.knowledgemap.learningsubject.implementation.dto.StudentObjectiveDto;
import com.edutie.infrastructure.knowledgemap.learningsubject.schema.StudentObjectiveGenerationSchema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

@Slf4j
@RequiredArgsConstructor
@Component
public class StudentObjectiveGenerationServiceImplementation extends ExternalService implements StudentObjectiveGenerationService {
    @Value("${knowledge-map-url}")
    private String KNOWLEDGE_MAP_URL;

    @Override
    public WrapperResult<String> generateContext(StudentObjectiveGenerationSchema schema) {
        final String studentObjectiveUrl = KNOWLEDGE_MAP_URL + "/knowledge-context/student-objective";
        log.info("Generating student objective for {}", schema);
        return new ExternalInfrastructureHandler<String, StudentObjectiveGenerationSchema, StudentObjectiveDto>(this.getClass())
                .setActionUrl(studentObjectiveUrl)
                .handle(schema, StudentObjectiveDto.class);
    }
}
