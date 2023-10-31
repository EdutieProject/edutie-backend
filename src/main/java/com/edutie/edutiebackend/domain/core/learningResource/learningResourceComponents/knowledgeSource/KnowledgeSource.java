package com.edutie.edutiebackend.domain.core.learningResource.learningResourceComponents.knowledgeSource;

import com.edutie.edutiebackend.domain.core.common.ValueObject;

import java.net.URL;
import java.util.Enumeration;
import java.util.Vector;

public class KnowledgeSource extends ValueObject {
    private URL source;
    private SourceOrigin origin;

    @Override
    public String toString() {
        return source.toString();
    }

    @Override
    public Enumeration<Object> GetEqualityComponents() {
        Vector<Object> components = new Vector<>();
        components.add(source);
        components.add(origin);
        return components.elements();
    }
}
