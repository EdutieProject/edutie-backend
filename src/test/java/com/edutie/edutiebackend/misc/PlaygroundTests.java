package com.edutie.edutiebackend.misc;

import com.edutie.edutiebackend.misc.definitions.Foo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PlaygroundTests {

    @Test
    public void recordEqualsTest()
    {
        var f1 = new Foo("Hello!");
        var f2 = new Foo("Hello!");
        Assertions.assertEquals(f1, f2);
    }

    @Test
    public void recordNotEqualsTest()
    {
        var f1 = new Foo("Hi");
        var f2 = new Foo("Mom!");
        Assertions.assertNotEquals(f1, f2);
    }
}
