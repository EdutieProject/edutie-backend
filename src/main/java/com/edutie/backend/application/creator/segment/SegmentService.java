package com.edutie.backend.application.creator.segment;

import com.edutie.backend.application.creator.segment.commands.*;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import com.edutie.backend.domain.studyprogram.lessonsegment.LessonSegment;
import validation.Result;
import validation.WrapperResult;

import java.util.List;

public interface SegmentService {
    List<LessonSegment> getAllSegmentsOfCreator(EducatorId educatorId);
    WrapperResult<LessonSegment> createSegmentAsNext(CreateSegmentAsNextCommand command);
    WrapperResult<LessonSegment> createSegmentInBetween(CreateSegmentInBetweenCommand command);
    Result changeSegmentProperties(ChangeSegmentPropertiesCommand command);
    Result moveSegment(MoveSegmentCommand command);
    Result removeSegment(RemoveSegmentCommand command);
}
