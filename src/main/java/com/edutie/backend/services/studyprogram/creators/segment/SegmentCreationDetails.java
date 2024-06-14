package com.edutie.backend.services.studyprogram.creators.segment;

import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(fluent = true)
public class SegmentCreationDetails {
    private Educator educator;
    private Segment previousSegment;
    private List<Segment> nextSegments;
    private String name;
}
