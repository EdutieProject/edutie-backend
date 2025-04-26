package com.edutie.mocks;

import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.administration.administrator.Administrator;
import com.edutie.domain.core.administration.administrator.persistence.AdministratorPersistence;
import com.edutie.domain.core.education.educator.Educator;
import com.edutie.domain.core.education.educator.persistence.EducatorPersistence;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.domain.core.learning.student.persistence.StudentPersistence;
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
    private boolean saved = false;

    @Getter
    private final UserId userId = new UserId();
    @Getter
    private final Administrator administratorProfile = Administrator.create(userId);
    @Getter
    private final Educator educatorProfile = Educator.create(userId);
    @Getter
    private final Student studentProfile = Student.create(userId);

    public void saveToPersistence() {
        administratorPersistence.save(administratorProfile).throwIfFailure();
        educatorPersistence.save(educatorProfile).throwIfFailure();
        studentPersistence.save(studentProfile).throwIfFailure();
        saved = true;
    }
}
