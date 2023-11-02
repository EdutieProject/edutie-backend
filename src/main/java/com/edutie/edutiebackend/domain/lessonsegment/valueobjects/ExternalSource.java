package com.edutie.edutiebackend.domain.lessonsegment.valueobjects;

import com.edutie.edutiebackend.domain.common.base.ValueObject;
import com.edutie.edutiebackend.domain.lessonsegment.enums.SourceOrigin;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;

@AllArgsConstructor
@Getter
public class ExternalSource extends ValueObject {
    private URL url;
    private SourceOrigin origin;

    @Override
    public String toString() {
        return url.toString();
    }

    @Override
    public Iterator<Object> GetEqualityComponents() {
        return Arrays.stream(new Object[]{url, origin}).iterator();
    }
}
