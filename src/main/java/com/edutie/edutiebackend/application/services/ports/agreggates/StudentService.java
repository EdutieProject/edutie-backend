package com.edutie.edutiebackend.application.services.ports.agreggates;

import com.edutie.edutiebackend.application.services.ports.crud.GenericCrudService;
import com.edutie.edutiebackend.domain.core.student.Student;

import java.util.UUID;

public interface StudentService extends GenericCrudService<Student, UUID> {
}
