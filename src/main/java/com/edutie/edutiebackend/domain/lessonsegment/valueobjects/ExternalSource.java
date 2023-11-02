package com.edutie.edutiebackend.domain.lessonsegment.valueobjects;

import com.edutie.edutiebackend.domain.lessonsegment.enums.SourceOrigin;

import java.net.URL;

/**
 * Source to the external knowledge resource.
 * @param url url to the source
 * @param origin the origin of the source
 */
public record ExternalSource(URL url, SourceOrigin origin) {
}
