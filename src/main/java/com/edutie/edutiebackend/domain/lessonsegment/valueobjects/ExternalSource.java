package com.edutie.edutiebackend.domain.lessonsegment.valueobjects;

import com.edutie.edutiebackend.domain.common.base.ValueObject;
import com.edutie.edutiebackend.domain.lessonsegment.enums.SourceOrigin;

import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;

public class ExternalSource extends ValueObject {
    private URL source;
    private SourceOrigin origin;

    @Override
    public String toString() {
        return source.toString();
    }

    @Override
    public Iterator<Object> GetEqualityComponents() {
        return Arrays.stream(new Object[]{source, origin}).iterator();
    }
}
