package com.edutie.edutiebackend.domain.common;

import java.util.Iterator;

/**
 * ValueObject base class. All fields of the implementation must value-based (not by reference).
 */
public abstract class ValueObject {
    @Override
    public boolean equals(Object o) { // <5>
        if (o == null || getClass() != o.getClass()) return false;
        ValueObject that = (ValueObject) o;
        return GetEqualityComponents().equals((that.GetEqualityComponents()));
    }

    public abstract Iterator<Object> GetEqualityComponents();

    /**
     * A method returning hashcode inspired by Object.hashCode() base implementation.
     * @return hashCode based on GetEqualityComponents
     */
    @Override
    public int hashCode() {
        int hashCode = 17; // Start with a prime number to minimize collisions
        Iterator<Object> components = GetEqualityComponents();

        while (components.hasNext()) {
            Object component = components.next();
            int componentHashCode = (component != null) ? component.hashCode() : 0;
            hashCode = 31 * hashCode + componentHashCode; // Use the same prime factor (31) as Java's Object.hashCode()
        }

        return hashCode;
    }
}
