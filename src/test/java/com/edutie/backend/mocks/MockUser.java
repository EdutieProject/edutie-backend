package com.edutie.backend.mocks;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.administration.administrator.persistence.AdministratorPersistence;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.persistence.StudentPersistence;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class MockUser {
    private final AdministratorPersistence administratorPersistence;
    private final StudentPersistence studentPersistence;
    private final EducatorPersistence educatorPersistence;

    @Getter
    private final UserId userId = new UserId();
    @Getter
    private final Administrator administratorProfile = Administrator.create(userId);
    @Getter
    private final Educator educatorProfile = Educator.create(userId, administratorProfile);
    @Getter
    private final Student studentProfile = Student.create(userId);

    public void saveToPersistence() {
        administratorPersistence.save(administratorProfile).throwIfFailure();
        educatorPersistence.save(educatorProfile).throwIfFailure();
        studentPersistence.save(studentProfile).throwIfFailure();
    }
}
