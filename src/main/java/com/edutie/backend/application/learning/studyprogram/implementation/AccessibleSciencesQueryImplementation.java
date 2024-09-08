package com.edutie.backend.application.learning.studyprogram.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.learning.studyprogram.AccessibleSciencesQueryHandler;
import com.edutie.backend.application.learning.studyprogram.queries.AccessibleSciencesQuery;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.science.persistence.SciencePersistence;
import validation.Result;
import validation.WrapperResult;
import org.springframework.stereotype.*;
import lombok.*;
import lombok.extern.slf4j.*;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class AccessibleSciencesQueryImplementation extends HandlerBase implements AccessibleSciencesQueryHandler {
	private final SciencePersistence sciencePersistence;

	@Override
	public WrapperResult<List<Science>> handle(AccessibleSciencesQuery query) {
		log.info("Retrieving all sciences");
		WrapperResult<List<Science>> scienceResult = sciencePersistence.getAll();
		if (scienceResult.isFailure()) {
			log.info("Persistence error: {}", scienceResult.getError().toString());
			return scienceResult;
		}
		log.info("Sciences retrieved successfully");
		return Result.successWrapper(scienceResult.getValue());
	}
}
