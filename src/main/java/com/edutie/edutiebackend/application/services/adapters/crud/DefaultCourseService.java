package com.edutie.edutiebackend.application.services.adapters.crud;

import com.edutie.edutiebackend.application.services.ports.crud.CourseService;
import com.edutie.edutiebackend.domain.core.course.Course;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class DefaultCourseService implements CourseService {
    /**
     * @param uuid
     * @return
     */
    @Override
    public Optional<Course> getById(UUID uuid) {
        return Optional.empty();
    }

    /**
     * @return
     */
    @Override
    public Set<Course> getAll() {
        return null;
    }

    /**
     * @param uuid
     * @param entity
     * @return
     */
    @Override
    public Optional<Course> overwrite(UUID uuid, Course entity) {
        return Optional.empty();
    }

    /**
     * @param uuid
     * @return
     */
    @Override
    public boolean delete(UUID uuid) {
        return false;
    }
}
