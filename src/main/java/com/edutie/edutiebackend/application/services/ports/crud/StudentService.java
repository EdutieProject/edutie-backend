package com.edutie.edutiebackend.application.services.ports.crud;

import com.edutie.edutiebackend.application.services.ports.crud.base.BaseService;
import com.edutie.edutiebackend.domain.core.student.Student;

import java.util.UUID;

public interface StudentService extends BaseService<Student, UUID> {
}
