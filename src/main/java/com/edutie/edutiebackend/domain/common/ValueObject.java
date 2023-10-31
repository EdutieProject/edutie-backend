package com.edutie.edutiebackend.domain.common;

import java.util.Enumeration;

public abstract class ValueObject {
    @Override
    public boolean equals(Object o) { // <5>
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValueObject that = (ValueObject) o;
        return GetEqualityComponents().equals(((ValueObject) o).GetEqualityComponents());
    }

    public abstract Enumeration<Object> GetEqualityComponents();

    //TODO!
    @Override
    public int hashCode() {
        return 1;
    }
}
