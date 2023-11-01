package com.edutie.edutiebackend.domain.learningresource.valueobjects;

import com.edutie.edutiebackend.domain.common.ValueObject;

import java.util.Enumeration;
import java.util.Vector;

/**
 * An overview of the learning resource providing
 * necessary information to introduce learner to the topic
 * e.g. inform about the method of completing the task
 * or, in general, about what the learner is about to learn
 * <p>
 *     <b>Text can be accessed via toString() method.</b>
 * </p>
 */
public final class Overview extends ValueObject {
    private String overview;

    /**
     * Sets the overview text to provided string
     * @param s string to be set as overview
     */
    public void set(String s)
    {
        overview = s;
    }

    @Override
    public String toString() {
        return overview;
    }

    @Override
    public Enumeration<Object> GetEqualityComponents() {
        Vector<Object> components = new Vector<>();
        components.add(overview);
        return components.elements();
    }
}
