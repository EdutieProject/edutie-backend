package com.edutie.edutiebackend.domain.student.studentProfiles;

import com.edutie.edutiebackend.domain.common.EntityBase;
import com.edutie.edutiebackend.domain.student.interfaces.IStudentProfile;
import com.edutie.edutiebackend.domain.student.studentTraits.Intelligence;
import jakarta.persistence.Entity;

import java.util.HashMap;

@Entity
public class IntelligenceProfile extends EntityBase<IntelligenceProfile> implements IStudentProfile {
    private final HashMap<Intelligence, Double> intelligencePoints = new HashMap<>();

    /**
     * Default constructor. Should be changed in the future to utilize database values.
     */
    public IntelligenceProfile() {
        for (Intelligence intelligence :
                Intelligence.values()) {
            intelligencePoints.put(intelligence, 0.0);
        }
    }

    @Override
    public void adjust(byte learningResult) {
        // perform adjusting
    }
}
