package com.edutie.backend.services.studyprogram.initializers.segment;

import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.domain.studyprogram.segment.persistence.SegmentPersistence;
import com.edutie.backend.services.common.ServiceBase;
import com.edutie.backend.services.common.logging.ExternalFailureLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Result;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class RootSegmentInitializerImplementation extends ServiceBase implements RootSegmentInitializer {
    private final SegmentPersistence segmentPersistence;

    @Override
    public WrapperResult<Segment> initializeSegment(RootSegmentInitializationDetails initializationDetails) {
        LOGGER.info("Initializing root segment for lesson of id {}", initializationDetails.lesson().getId());
        Segment segment = Segment.create(initializationDetails.educator(), initializationDetails.lesson());
        segment.setName("First segment. Start designing it now!");
        Result segmentSaveResult = segmentPersistence.save(segment);
        if (segmentSaveResult.isFailure()) {
            return ExternalFailureLog.persistenceFailure(segmentSaveResult, LOGGER, "Root segment saving error").map(() -> null);
        }
        return WrapperResult.successWrapper(segment);
    }
}
