package com.edutie.edutiebackend.domain.core.lessonsegment.valueobjects;

import com.edutie.edutiebackend.domain.core.common.base.EntityBase;
import com.edutie.edutiebackend.domain.core.lessonsegment.enums.SourceOrigin;
import lombok.*;

import java.net.URL;

/**
 * Source to the external knowledge resource.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExternalSource extends EntityBase<ExternalSourceId> {
    URL url;
    SourceOrigin origin;
}

