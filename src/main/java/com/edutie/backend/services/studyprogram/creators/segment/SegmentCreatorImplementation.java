package com.edutie.backend.services.studyprogram.creators.segment;

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
public class SegmentCreatorImplementation extends ServiceBase implements SegmentCreator {
    private final SegmentPersistence segmentPersistence;

    @Override
    public WrapperResult<Segment> createSegment(SegmentCreationDetails creationDetails) {
        if (creationDetails.nextSegment() == null)
            LOGGER.info("Creating a segment as a segment tree leaf. Previous elem id: {}", creationDetails.previousSegment().getId());
        else
            LOGGER.info("Creating a segment in between 2 segments. Previous elem id: {}, next elem id: {}", creationDetails.previousSegment().getId(), creationDetails.nextSegment().getId());

        Segment segment = Segment.create(creationDetails.educator(), creationDetails.previousSegment());
        segment.setName("First segment. Start designing it now!");
        if (creationDetails.nextSegment() != null) {
            Result addElemResult = segment.addNextElement(creationDetails.nextSegment());
            if (addElemResult.isFailure())
                return addElemResult.map(() -> null);
        }

        Result segmentSaveResult = segmentPersistence.save(segment);
        if (segmentSaveResult.isFailure()) {
            return ExternalFailureLog.persistenceFailure(segmentSaveResult, LOGGER).map(() -> null);
        }
        return WrapperResult.successWrapper(segment);
    }
}
