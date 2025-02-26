package com.edutie.domain.service.studyprogram.progressindication.course;

import com.edutie.domain.core.learning.student.Student;
import com.edutie.backend.domain.studyprogram.course.Course;
import validation.WrapperResult;

public interface CourseProgressIndicationService {
    WrapperResult<Double> getCourseProgressFactor(Course course, Student student);
}
