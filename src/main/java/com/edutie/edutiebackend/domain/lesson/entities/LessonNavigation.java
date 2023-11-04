package com.edutie.edutiebackend.domain.lesson.entities;

import com.edutie.edutiebackend.domain.common.identities.LessonId;
import com.edutie.edutiebackend.domain.common.studynavigation.StudyNavigation;

import java.util.HashSet;
import java.util.Set;

public class LessonNavigation implements StudyNavigation<LessonId> {

    private LessonId previousLessonId;
    private Set<LessonId> nextLessonsIds = new HashSet<>();

    /**
     * Adds a lesson as next lesson
     * @param lessonId id of lesson to add as next
     */
    @Override
    public void addNextElement(LessonId lessonId) {
        nextLessonsIds.add(lessonId);
    }

    /**
     * Removes a lesson from next lesson set
     * @param lessonId identifier of lesson to remove
     */
    @Override
    public void removeNextElement(LessonId lessonId) {
        nextLessonsIds.remove(lessonId);
    }

    /**
     * sets lesson id as previous
     * @param lessonId lesson to be set as previous
     */
    @Override
    public void setPreviousElement(LessonId lessonId) {
        previousLessonId = lessonId;
    }

    /**
     * Sets next Lessons collection
     * @param nextElements collection to be set as next elements
     */
    @Override
    public void setNextElements(Set<LessonId> nextElements) {
        nextLessonsIds = nextElements;
    }

    /**
     * Retrieves previous Lesson identifier
     * @return previous lesson identifier
     */
    @Override
    public LessonId getPreviousElement() {
        return previousLessonId;
    }

    /**
     * Retrieves next lessons set
     * @return Set of next lessons
     */
    @Override
    public Set<LessonId> getNextElements() {
        return nextLessonsIds;
    }
}
