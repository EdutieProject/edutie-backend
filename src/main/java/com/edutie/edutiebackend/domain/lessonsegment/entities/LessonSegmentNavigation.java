package com.edutie.edutiebackend.domain.lessonsegment.entities;

import com.edutie.edutiebackend.domain.common.identities.LessonSegmentId;
import com.edutie.edutiebackend.domain.common.studynavigation.StudyNavigation;

import java.util.HashSet;
import java.util.Set;

/**
 * Class responsible for navigation, encapsulating data and behaviour
 * of navigating through lesson segments.
 */
public class LessonSegmentNavigation implements StudyNavigation<LessonSegmentId> {

    private Set<LessonSegmentId> nextSegmentsIds = new HashSet<>();
    private LessonSegmentId previousSegmentId;

    /**
     * Adds given identifier as one of the next lesson segments after this one.
     * @param lessonSegmentId identifier to add
     */
    @Override
    public void addNextElement(LessonSegmentId lessonSegmentId) {
        nextSegmentsIds.add(lessonSegmentId);
    }

    /**
     * Removes given lesson segment identifier from the set of next segments identifiers.
     * @param lessonSegmentId id to remove
     */
    @Override
    public void removeNextElement(LessonSegmentId lessonSegmentId) {
        nextSegmentsIds.remove(lessonSegmentId);
    }

    @Override
    public void setPreviousElement(LessonSegmentId lessonSegmentId) {
        previousSegmentId = lessonSegmentId;
    }

    @Override
    public void setNextElements(Set<LessonSegmentId> nextElements) {
        this.nextSegmentsIds = nextElements;
    }


    /**
     * Retrieves previous Lesson Segment id
     * @return identifier of previous lesson segment
     */
    @Override
    public LessonSegmentId getPreviousElement() {
        return previousSegmentId;
    }


    /**
     * Retrieves all the elements coming as next after this lesson segment.
     * @return Set of next segments identifiers
     */
    @Override
    public Set<LessonSegmentId> getNextElements() {
        return nextSegmentsIds;
    }
}
