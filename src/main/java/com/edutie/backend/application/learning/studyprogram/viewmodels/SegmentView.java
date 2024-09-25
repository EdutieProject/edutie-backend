package com.edutie.backend.application.learning.studyprogram.viewmodels;

import com.edutie.backend.domain.studyprogram.segment.Segment;

public record SegmentView(
		Segment segment,
		int approachesTaken,
		int approachesSucceeded,
		boolean done
) { }
