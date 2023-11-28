package com.edutie.edutiebackend.application.services.ports.crud;

import com.edutie.edutiebackend.application.services.ports.crud.base.BaseService;
import com.edutie.edutiebackend.domain.core.lessonsegment.LessonSegment;

import java.util.UUID;

public interface LessonSegmentCrudService extends BaseService<LessonSegment, UUID> {
}
