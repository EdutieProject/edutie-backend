package com.edutie.backend.infrastructure.persistence.implementation.jpa;

import com.edutie.backend.domain.administration.AdminId;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.exercisetype.ExerciseType;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.EducatorRepository;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.ExerciseTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ExerciseTypeJpaTests {
    private final UserId testUserId = new UserId();
    private final AdminId adminId = new AdminId();
    private ExerciseType exerciseType;
    private Educator educator;
    private final UserId testUserId_2 = new UserId();
    @Autowired
    private ExerciseTypeRepository exerciseTypeRepository;
    @Autowired
    private EducatorRepository educatorRepository;

    @BeforeEach
    public void testSetup() {
        educator = Educator.create(testUserId, adminId);
        educatorRepository.save(educator);
        exerciseType = ExerciseType.create(educator).getValue();
        exerciseTypeRepository.save(exerciseType);
    }

    @Test
    public void testCreate() {
        exerciseType = ExerciseType.create(educator).getValue();
        exerciseTypeRepository.save(exerciseType);

        assertEquals(exerciseTypeRepository.findById(exerciseType.getId()).orElseThrow(), exerciseType);
        assertEquals((exerciseTypeRepository.findById(exerciseType.getId()).orElseThrow()).getCreatedOn(), exerciseType.getCreatedOn());
    }

    @Test
    public void testUserUpdate() {
        var fetched = exerciseTypeRepository.findById(exerciseType.getId()).orElseThrow();
        assertEquals(fetched.getCreatedOn(), exerciseType.getCreatedOn()); //check if exerciseType time match DB
        assertEquals(fetched.getCreatedBy(), testUserId);//check if exerciseType was created by user1 (testUserId)

        exerciseType.update(testUserId_2); // update User1 to User2
        exerciseTypeRepository.save(exerciseType);// saving new exerciseType to DB

        fetched = exerciseTypeRepository.findById(exerciseType.getId()).orElseThrow();
        assertEquals(fetched.getUpdatedBy(), testUserId_2); // check if UserId has been updated
        assertEquals(fetched.getUpdatedOn(), exerciseType.getUpdatedOn());//check if exerciseType time has been updated in DB
    }


}
