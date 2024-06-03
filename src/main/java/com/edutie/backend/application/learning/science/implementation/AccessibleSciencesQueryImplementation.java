package com.edutie.backend.application.learning.science.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.learning.science.AccessibleSciencesQueryHandler;
import com.edutie.backend.application.learning.science.queries.AccessibleSciencesQuery;
import com.edutie.backend.domain.studyprogram.course.Course;
import com.edutie.backend.domain.studyprogram.science.Science;
import com.edutie.backend.domain.studyprogram.science.persistence.SciencePersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Result;
import validation.WrapperResult;

import java.util.List;

@RequiredArgsConstructor
@Component
public class AccessibleSciencesQueryImplementation extends HandlerBase implements AccessibleSciencesQueryHandler {
    private final SciencePersistence sciencePersistence;
    @Override
    public WrapperResult<List<Science>> handle(AccessibleSciencesQuery query){
        LOGGER.info("Retrieving all sciences");
        WrapperResult<List<Science>> scienceResult = sciencePersistence.getAll();
        if (scienceResult.isFailure()) {
            LOGGER.info("Persistence error: " + scienceResult.getError().toString());
            return scienceResult;
        }
        LOGGER.info("Sciences retrieved successfully");
        return Result.successWrapper(scienceResult.getValue());
    }
}