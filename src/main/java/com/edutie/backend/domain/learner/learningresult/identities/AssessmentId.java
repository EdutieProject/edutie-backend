package com.edutie.backend.domain.learner.learningresult.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class AssessmentId extends UuidIdentifier {
    public AssessmentId(){
        super();
    }
    public AssessmentId(UUID uuid){
        super(uuid);
    }
}
