package com.edutie.backend.infra.persistence.jpa;

import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.studyprogram.exercisetype.ExerciseType;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.ExerciseTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ExerciseTypeTests {
    private final UserId testUserId = new UserId();
    private ExerciseType exerciseType;
    private final UserId testUserId_2 = new UserId();
    @Autowired
    private ExerciseTypeRepository exerciseTypeRepository;

    @BeforeEach
    public void testSetup() {
        exerciseType = ExerciseType.create(testUserId);
        exerciseTypeRepository.save(exerciseType);
    }

    @Test
    public void testCreate() {
        exerciseType = ExerciseType.create(testUserId);
        exerciseTypeRepository.save(exerciseType);

        assertEquals(exerciseTypeRepository.findById(exerciseType.getId()).orElseThrow(), exerciseType);
        assertEquals((exerciseTypeRepository.findById(exerciseType.getId()).orElseThrow()).getCreatedOn(), exerciseType.getCreatedOn());
    }

    @Test
    public void testUserUpdate() {
        var fetched = exerciseTypeRepository.findById(exerciseType.getId()).orElseThrow();
        assertEquals(fetched.getCreatedOn(), exerciseType.getCreatedOn()); //check if exerciseType time match DB
        assertEquals(fetched.getCreatedBy(), testUserId);//check if exerciseType was created by user1 (testUserId)

        exerciseType.UserUpdate(testUserId_2); // update User1 to User2
        exerciseTypeRepository.save(exerciseType);// saving new exerciseType to DB

        fetched = exerciseTypeRepository.findById(exerciseType.getId()).orElseThrow();
        assertEquals(fetched.getUpdatedBy(), testUserId_2); // check if UserId has been updated
        assertEquals(fetched.getUpdatedOn(), exerciseType.getUpdatedOn());//check if exerciseType time has been updated in DB
    }

    //TODO testDescriptionUpdate !

    @Test
    public void testDescriptionUpdate() {
    }

}
