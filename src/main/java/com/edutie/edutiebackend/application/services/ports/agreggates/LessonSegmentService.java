package com.edutie.edutiebackend.application.services.ports.agreggates;

import com.edutie.edutiebackend.application.services.ports.crud.GenericCrudService;
import com.edutie.edutiebackend.domain.core.lessonsegment.LessonSegment;

import java.util.UUID;

public interface LessonSegmentService extends GenericCrudService<LessonSegment, UUID> {
}
