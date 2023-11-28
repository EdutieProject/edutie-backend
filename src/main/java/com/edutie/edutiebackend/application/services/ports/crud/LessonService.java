package com.edutie.edutiebackend.application.services.ports.crud;

import com.edutie.edutiebackend.application.services.ports.crud.base.BaseService;
import com.edutie.edutiebackend.domain.core.lesson.Lesson;

import java.util.UUID;

public interface LessonService extends BaseService<Lesson, UUID> {
}
