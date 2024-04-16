package com.edutie.backend.application.management.segment.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.segment.CreateSegmentCommandHandler;
import com.edutie.backend.application.management.segment.commands.CreateSegmentCommand;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.lesson.Lesson;
import com.edutie.backend.domain.studyprogram.lesson.persistence.LessonPersistence;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.domain.studyprogram.segment.persistence.SegmentPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Error;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class CreateSegmentCommandHandlerImplementation extends HandlerBase implements CreateSegmentCommandHandler {
    private final SegmentPersistence segmentPersistence;
    private final EducatorPersistence educatorPersistence;
    @Override
    public WrapperResult<Segment> handle(CreateSegmentCommand command) {
        Educator educator = educatorPersistence.getByUserId(command.educatorUserId());
        WrapperResult<Segment> previousSegmentWrapper = segmentPersistence.getById(command.previousSegmentId());
        if (previousSegmentWrapper.isFailure())
            return previousSegmentWrapper;
        Segment segment = Segment.create(educator, previousSegmentWrapper.getValue().getLesson());
        segment.setName(command.segmentName());
        segmentPersistence.save(segment);
        return WrapperResult.successWrapper(segment);
    }
}
