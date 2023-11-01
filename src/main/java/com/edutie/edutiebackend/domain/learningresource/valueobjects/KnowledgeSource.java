package com.edutie.edutiebackend.domain.learningresource.valueobjects;

import com.edutie.edutiebackend.domain.common.ValueObject;
import com.edutie.edutiebackend.domain.learningresource.enums.SourceOrigin;

import java.net.URL;
import java.util.Iterator;
import java.util.Vector;

public class KnowledgeSource extends ValueObject {
    private URL source;
    private SourceOrigin origin;

    @Override
    public String toString() {
        return source.toString();
    }

    @Override
    public Iterator<Object> GetEqualityComponents() {
        Vector<Object> components = new Vector<>();
        components.add(source);
        components.add(origin);
        return components.iterator();
    }
}
