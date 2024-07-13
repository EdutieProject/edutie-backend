package com.edutie.backend.infrastucture.knowledgemap.implementation;

import com.edutie.backend.domain.personalization.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.personalization.knowledgesubject.KnowledgeSubjectId;
import com.edutie.backend.infrastucture.knowledgemap.KnowledgeMapService;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.List;
import java.util.UUID;

@Component
public class KnowledgeMapServiceImplementation implements KnowledgeMapService {
    @Override
    public WrapperResult<List<KnowledgeCorrelation>> getKnowledgeCorrelations(KnowledgeSubjectId knowledgeSubjectId) {
        return WrapperResult.successWrapper(List.of(
                new KnowledgeCorrelation(new KnowledgeSubjectId(UUID.fromString("73658904-a20e-41f0-8274-6c000e0760da")), 2),
                new KnowledgeCorrelation(new KnowledgeSubjectId(UUID.fromString("4e92752a-5ef8-420e-ba45-260b6b7af5fe")), 4),
                new KnowledgeCorrelation(new KnowledgeSubjectId(UUID.fromString("201b3e63-5340-4a35-8f51-8de8275dae1e")), 7),
                new KnowledgeCorrelation(new KnowledgeSubjectId(UUID.fromString("7ad5fd80-6337-4b69-8048-8a97e39aa963")), 9)
        ));
    }
}
