package com.edutie.backend.domain.core;

import com.edutie.backend.domain.core.science.Science;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class ScienceTests {

    @Test
    public void scienceTest()
    {
        Science science = new Science(
                "Matematyka",
                "Królowa nauk"
        );

        assertEquals(science.getName(), "Matematyka");
    }
}
