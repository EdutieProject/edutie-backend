package com.edutie.edutiebackend.domain.student.entites;

import com.edutie.edutiebackend.domain.common.identities.IntelligenceProfileId;
import com.edutie.edutiebackend.domain.common.studenttraits.Intelligence;
import com.edutie.edutiebackend.domain.student.entites.base.StudentTraitProfile;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.EnumMap;

/**
 * Profile for students made to track their intelligence and cognition.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class IntelligenceProfile extends StudentTraitProfile<Intelligence, IntelligenceProfileId> {
    /**
     * Base constructor of intelligence profile, initializes mapping.
     */
    public IntelligenceProfile()
    {
        parameters = new EnumMap<>(Intelligence.class);
    }
}
