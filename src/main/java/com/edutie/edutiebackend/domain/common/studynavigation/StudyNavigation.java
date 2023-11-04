package com.edutie.edutiebackend.domain.common.studynavigation;

import java.util.Set;

/**
 * Interface used by tree-like learning structures
 * which have multiple next elements and one previous element.
 * Interface has methods responsible for next and previous elements
 * retrieval and modification.
 * @param <TLearningElemID> Type of identifier of single learning element
 */
public interface StudyNavigation<TLearningElemID> {
    void addNextElement(TLearningElemID elemID);
    void removeNextElement(TLearningElemID elemID);
    void setPreviousElement(TLearningElemID elemID);
    void setNextElements(Set<TLearningElemID> nextElements);
    TLearningElemID getPreviousElement();
    Set<TLearningElemID> getNextElements();
}
