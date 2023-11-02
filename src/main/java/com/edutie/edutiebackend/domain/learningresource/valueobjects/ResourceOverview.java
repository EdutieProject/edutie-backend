package com.edutie.edutiebackend.domain.learningresource.valueobjects;

import com.edutie.edutiebackend.domain.common.base.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Iterator;

/**
 * An overview of the learning resource providing
 * necessary information to introduce learner to the topic
 * e.g. inform about the method of completing the task
 * or, in general, about what the learner is about to learn
 * <p>
 *     <b>Text can be accessed via toString() method.</b>
 * </p>
 */
@AllArgsConstructor
@Getter
public final class ResourceOverview extends ValueObject {
    private String overview;

    @Override
    public String toString() {
        return overview;
    }

    @Override
    public Iterator<Object> GetEqualityComponents() {
        return Arrays.stream(new Object[]{overview}).iterator();
    }
}
