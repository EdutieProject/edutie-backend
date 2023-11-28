package com.edutie.edutiebackend.api.controllers;

import com.edutie.edutiebackend.application.services.ports.crud.CourseService;
import com.edutie.edutiebackend.domain.core.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController("courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService)
    {
        this.courseService = courseService;
    }

    @GetMapping
    public Set<Course> getAllCourses()
    {
        return courseService.getAll();
    }

}
