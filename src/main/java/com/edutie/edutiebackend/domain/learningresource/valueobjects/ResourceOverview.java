package com.edutie.edutiebackend.domain.learningresource.valueobjects;


/**
 * An overview of a learning resource. Exposed to the user as the introduction
 * to the topic.
 * May be generated regarding student's abilities or generated statically.
 * @param text text of the overview
 */
public record ResourceOverview(String text) {
}
