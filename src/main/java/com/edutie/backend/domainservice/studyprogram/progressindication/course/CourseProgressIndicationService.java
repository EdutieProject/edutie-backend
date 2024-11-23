package com.edutie.backend.domainservice.studyprogram.progressindication.course;

import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.studyprogram.course.Course;
import validation.WrapperResult;

public interface CourseProgressIndicationService {
    WrapperResult<Double> getCourseProgressFactor(Course course, Student student);
}
