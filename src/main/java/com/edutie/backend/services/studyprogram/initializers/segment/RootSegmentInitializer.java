package com.edutie.backend.services.studyprogram.initializers.segment;

import com.edutie.backend.domain.studyprogram.segment.Segment;
import validation.WrapperResult;

@FunctionalInterface
public interface RootSegmentInitializer {
    WrapperResult<Segment> initializeSegment(RootSegmentInitializationDetails initializationDetails);
}
