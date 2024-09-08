package com.edutie.backend.application.management.segment.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.segment.CreatedSegmentsQueryHandler;
import com.edutie.backend.application.management.segment.queries.CreatedSegmentsQuery;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.domain.studyprogram.segment.persistence.SegmentPersistence;
import validation.WrapperResult;
import org.springframework.stereotype.*;
import lombok.*;
import lombok.extern.slf4j.*;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreatedSegmentsQueryHandlerImplementation extends HandlerBase implements CreatedSegmentsQueryHandler {
	private final SegmentPersistence segmentPersistence;
	private final EducatorPersistence educatorPersistence;

	@Override
	public WrapperResult<List<Segment>> handle(CreatedSegmentsQuery query) {
		log.info("Retrieving segments created by educator of id {}", query.educatorUserId().identifierValue());
		Educator educator = educatorPersistence.getByAuthorizedUserId(query.educatorUserId());
		return segmentPersistence.getAllOfEducatorId(educator.getId());
	}
}
