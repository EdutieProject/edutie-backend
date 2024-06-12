package com.edutie.backend.services.studyprogram.initializers.segment;

import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.domain.studyprogram.segment.persistence.SegmentPersistence;
import com.edutie.backend.services.common.ServiceBase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Result;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class SegmentInitializerImplementation extends ServiceBase implements SegmentInitializer {
    private final SegmentPersistence segmentPersistence;

    @Override
    public WrapperResult<Segment> initializeSegment(SegmentInitializationDetails initializationDetails) {
        Segment segment = Segment.create(initializationDetails.educator(), initializationDetails.lesson());
        segment.setName("First segment. Start designing it now!");
        Result segmentSaveResult = segmentPersistence.save(segment);
        if (segmentSaveResult.isFailure()) {
            LOGGER.info("Persistence failure while saving initial segment. Error: " + segmentSaveResult.getError().toString());
            return segmentSaveResult.map(() -> null);
        }
        return WrapperResult.successWrapper(segment);
    }
}
