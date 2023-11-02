package com.edutie.edutiebackend.domain.common.base;

import java.util.Iterator;

/**
 * ValueObject base class.
 * Value objects should be immutable by default. If replacement is needed, replace it with a new one.
 * @implNote  All fields of the implementation must value-based (not reference-based).
 */
public abstract class ValueObject {
    @Override
    public boolean equals(Object o) { // <5>
        if (o == null || getClass() != o.getClass()) return false;
        ValueObject that = (ValueObject) o;
        return checkEqualityComponents(GetEqualityComponents(), that.GetEqualityComponents());
    }

    private boolean checkEqualityComponents(Iterator<Object> iterator1, Iterator<Object> iterator2)
    {
        while (iterator1.hasNext() && iterator2.hasNext()) {
            Object element1 = iterator1.next();
            Object element2 = iterator2.next();

            if (!element1.equals(element2)) {
                // Elements are not equal, so the iterators are not equal
                return false;
            }
        }

        // If one iterator has more elements, they are not equal
        return !iterator1.hasNext() && !iterator2.hasNext();
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
