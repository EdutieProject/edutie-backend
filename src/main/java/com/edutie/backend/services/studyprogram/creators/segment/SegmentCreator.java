package com.edutie.backend.services.studyprogram.creators.segment;

import com.edutie.backend.domain.studyprogram.segment.Segment;
import validation.WrapperResult;

@FunctionalInterface
public interface SegmentCreator {
    WrapperResult<Segment> createSegment(SegmentCreationDetails initializationDetails);
}
