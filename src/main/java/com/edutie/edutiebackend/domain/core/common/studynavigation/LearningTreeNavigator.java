package com.edutie.edutiebackend.domain.core.common.studynavigation;

import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

/**
 * Generic class used in tree-like learning structures
 * responsible for navigating through elements. It provides
 * necessary behaviour to add, change, remove the elements
 * from navigation properties.
 * @param <TLearningElemId> id type of elements to navigate through
 */
@Embeddable
public class LearningTreeNavigator<TLearningElemId>{
    private Set<TLearningElemId> nextLearningElements = new HashSet<>();
    private TLearningElemId previousLearningElem;

    /**
     * Adds a next element to the next elements set.
     * @param learningElemId id of element to add
     */
    public void addNext(TLearningElemId learningElemId) {
        nextLearningElements.add(learningElemId);
    }

    /**
     * removes element from the set of next elements
     * @param learningElemId id of elem to remove
     */
    public void removeNextElement(TLearningElemId learningElemId) {
        nextLearningElements.remove(learningElemId);
    }

    /**
     * Sets previous element
     * @param learningElemId id to be set as previous
     */
    public void setPreviousElement(TLearningElemId learningElemId) {
        previousLearningElem = learningElemId;
    }

    /**
     * Sets the provided collection as the identifiers of next elements
     * @param nextElements set of elements ids to be set as next
     */
    public void setNextElements(Set<TLearningElemId> nextElements) {
        nextLearningElements = nextElements;
    }

    /**
     * Retrieve previous element identifier
     * @return previous Element identifier.
     */
    public TLearningElemId getPreviousElement() {
        return previousLearningElem;
    }

    /**
     * Retrieve elements coming after this one.
     * @return Next elements set
     */
    @OneToMany
    public Set<TLearningElemId> getNextElements() {
        return nextLearningElements;
    }
}
