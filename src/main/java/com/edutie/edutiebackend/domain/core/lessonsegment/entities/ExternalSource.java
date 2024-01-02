package com.edutie.edutiebackend.domain.core.lessonsegment.entities;

import com.edutie.edutiebackend.domain.core.common.base.EntityBase;
import com.edutie.edutiebackend.domain.core.lessonsegment.enums.SourceOrigin;
import com.edutie.edutiebackend.domain.core.lessonsegment.valueobjects.ExternalSourceId;
import jakarta.persistence.Entity;
import lombok.*;

import java.net.URL;

/**
 * Source to the external knowledge resource.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ExternalSource extends EntityBase<ExternalSourceId> {
    URL url;
    SourceOrigin origin;
}

