package com.edutie.edutiebackend.application.services.ports.agreggates;

import com.edutie.edutiebackend.application.services.ports.crud.GenericCrudService;
import com.edutie.edutiebackend.domain.core.lessonsegment.LessonSegment;
import com.edutie.edutiebackend.domain.core.lessonsegment.identities.LessonSegmentId;

public interface LessonSegmentService extends GenericCrudService<LessonSegment, LessonSegmentId> {
}
