package com.edutie.edutiebackend.domain.ruleframework.tests;

import com.edutie.edutiebackend.domain.rule.RuleError;
import com.edutie.edutiebackend.domain.ruleframework.mock.Color;
import com.edutie.edutiebackend.domain.ruleframework.mock.Fruit;
import com.edutie.edutiebackend.domain.ruleframework.rules.StrawberryColorRule;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RuleFrameworkTests {

    @Test
    public void sameScopeRuleCheckSuccess()
    {
        Fruit fruit = Fruit.orange();
        assertTrue(fruit.setColor(Color.ORANGE).isSuccess());
    }

    @Test
    public void sameScopeRuleCheckFailure()
    {
        Fruit fruit = Fruit.orange();
        assertFalse(fruit.setColor(Color.BLUE).isSuccess());
    }

    @Test
    public void getChangedValueTest()
    {
        // banana is not included in validation - there is no BananaColorRule
        Fruit banana = Fruit.strawberry();
        banana.setColor(Color.RED);
        assertEquals(banana.getColor(), Color.RED);
    }

    @Test
    public void errorCodeTest()
    {
        Fruit strawberry = Fruit.strawberry();
        var result = strawberry.setColor(Color.ORANGE);
        RuleError error;
        if(result.isFailure())
            error = result.getRuleErrors().get(0);
        else
            error = new RuleError("NoCodeProvided", "Invalid message");
        assertEquals(
                StrawberryColorRule.class.getSimpleName(),
                error.getCode()
        );
    }

}
