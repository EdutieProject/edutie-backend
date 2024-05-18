package com.edutie.backend.application.management;

import com.edutie.backend.application.management.science.CreateScienceCommandHandler;
import com.edutie.backend.application.management.science.commands.CreateScienceCommand;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.studyprogram.science.Science;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import validation.WrapperResult;

@SpringBootTest
public class ScienceManagementTests {
    @Autowired
    CreateScienceCommandHandler createScienceCommandHandler;

    private final UserId userId = new UserId();

    @Test
    public void createScienceTest() {
        CreateScienceCommand command = new CreateScienceCommand()
                .adminUserId(userId)
                .scienceName("Mathematics");

        WrapperResult<Science> courseWrapperResult = createScienceCommandHandler.handle(command);

        assert courseWrapperResult.isSuccess();
        assert courseWrapperResult.getValue().getName().equals("Mathematics");
    }
}
