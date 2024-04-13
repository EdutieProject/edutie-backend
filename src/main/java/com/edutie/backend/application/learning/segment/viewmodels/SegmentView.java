package com.edutie.backend.application.learning.segment.viewmodels;

import com.edutie.backend.domain.studyprogram.segment.Segment;

public record SegmentView(
        Segment segment,
        boolean done
        ) {
}
