package com.edutie.edutiebackend.domain.student.entites;

import com.edutie.edutiebackend.domain.common.base.EntityBase;
import com.edutie.edutiebackend.domain.common.studentTraits.Intelligence;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class IntelligenceProfile extends EntityBase<IntelligenceProfile>{
    private HashMap<Intelligence, Double> intelligencePoints;

    /**
     * Default constructor. Should be changed in the future to utilize database values.
     */
    public IntelligenceProfile() {
        intelligencePoints = new HashMap<>();
        for (Intelligence intelligence :
                Intelligence.values()) {
            intelligencePoints.put(intelligence, 0.0);
        }
    }
}
