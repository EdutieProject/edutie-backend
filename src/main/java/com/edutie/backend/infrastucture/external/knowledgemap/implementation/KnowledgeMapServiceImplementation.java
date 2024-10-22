package com.edutie.backend.infrastucture.external.knowledgemap.implementation;

import com.edutie.backend.domain.education.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.infrastucture.external.common.ExternalInfrastructureHandler;
import com.edutie.backend.infrastucture.external.knowledgemap.KnowledgeMapService;
import com.edutie.backend.infrastucture.external.knowledgemap.dto.KnowledgeCorrelationSetDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.Set;

@Component
@Slf4j
public class KnowledgeMapServiceImplementation implements KnowledgeMapService {
    @Value("${knowledge-map-host}")
    private String KNOWLEDGE_MAP_HOST;

    @Override
    public WrapperResult<Set<KnowledgeCorrelation>> getKnowledgeCorrelations(Set<KnowledgeSubjectId> knowledgeSubjectIds) {
        final String CORRELATIONS_URL = KNOWLEDGE_MAP_HOST + "/correlations";
        return new ExternalInfrastructureHandler<Set<KnowledgeCorrelation>, Set<KnowledgeSubjectId>, KnowledgeCorrelationSetDto>(this.getClass())
                .setActionUrl(CORRELATIONS_URL)
                .handle(knowledgeSubjectIds, KnowledgeCorrelationSetDto.class);
    }
}
