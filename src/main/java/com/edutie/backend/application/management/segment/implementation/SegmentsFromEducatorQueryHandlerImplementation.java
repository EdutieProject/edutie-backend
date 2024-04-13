package com.edutie.backend.application.management.segment.implementation;

import com.edutie.backend.application.common.HandlerBase;
import com.edutie.backend.application.management.segment.SegmentsFromEducatorQueryHandler;
import com.edutie.backend.application.management.segment.queries.SegmentsFromEducatorQuery;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.studyprogram.segment.Segment;
import com.edutie.backend.domain.studyprogram.segment.persistence.SegmentPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Error;
import validation.Result;
import validation.WrapperResult;

import java.util.List;
@Component
@RequiredArgsConstructor
public class SegmentsFromEducatorQueryHandlerImplementation extends HandlerBase implements SegmentsFromEducatorQueryHandler {
    private final SegmentPersistence segmentPersistence;
    private final EducatorPersistence educatorPersistence;
    @Override
    public WrapperResult<List<Segment>> handle(SegmentsFromEducatorQuery query) {
        try {
            Educator educator = educatorPersistence.getByUserId(query.educatorUserId());
            return Result.successWrapper(segmentPersistence.getAllOfCreatorId(educator.getId()));
        } catch (Exception exception) {
            return Result.failureWrapper(new Error("Sth went wrong", exception.getMessage()));
        }
    }
}
