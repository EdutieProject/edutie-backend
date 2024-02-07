package com.edutie.backend.misc;

import java.util.HashSet;

import com.edutie.backend.misc.definitions.Foo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.edutie.backend.misc.definitions.Bar;

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

    @Test
    public void recordWithObjectTest()
    {
        var hs1 = new HashSet<Double>();
        hs1.add(1.2);
        var hs2 = new HashSet<Double>();
        hs2.add(1.2);
        var b1 = new Bar(hs1);
        var b2 = new Bar(hs2);
        Assertions.assertEquals(b1, b2);
    }
}
