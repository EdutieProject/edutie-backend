package com.edutie.backend.application.creator.segment;

import com.edutie.backend.application.creator.lesson.commands.RemoveLessonCommand;
import com.edutie.backend.application.creator.segment.commands.*;
import com.edutie.backend.domain.studyprogram.creator.identities.CreatorId;
import com.edutie.backend.domain.studyprogram.lessonsegment.LessonSegment;
import validation.Result;
import validation.WrapperResult;

import java.util.List;

public interface LessonSegmentService {
    List<LessonSegment> getAllSegmentsOfCreator(CreatorId creatorId);
    WrapperResult<LessonSegment> createLessonSegmentAsNext(CreateLessonSegmentAsNextCommand command);
    WrapperResult<LessonSegment> createLessonSegmentInBetween(CreateLessonSegmentInBetweenCommand command);
    Result changeLessonSegmentProperties(ChangeLessonSegmentPropertiesCommand command);
    Result moveLessonSegment(MoveLessonSegmentCommand command);
    Result removeLessonSegment(RemoveLessonSegmentCommand command);
}
