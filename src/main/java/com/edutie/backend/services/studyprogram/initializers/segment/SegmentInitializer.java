package com.edutie.backend.services.studyprogram.initializers.segment;

import com.edutie.backend.domain.studyprogram.segment.Segment;
import validation.WrapperResult;

@FunctionalInterface
public interface SegmentInitializer {
    WrapperResult<Segment> initializeSegment(SegmentInitializationDetails initializationDetails);
}
