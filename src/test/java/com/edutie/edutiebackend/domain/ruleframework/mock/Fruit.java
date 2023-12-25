package com.edutie.edutiebackend.domain.ruleframework.mock;

import com.edutie.edutiebackend.domain.rule.Result;
import com.edutie.edutiebackend.domain.rule.Rule;
import com.edutie.edutiebackend.domain.ruleframework.rules.FruitTasteBoundsRule;
import com.edutie.edutiebackend.domain.ruleframework.rules.OrangeColorRule;
import com.edutie.edutiebackend.domain.ruleframework.rules.StrawberryColorRule;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Fruit {
    String name;
    Color color;
    int sourness;
    int sweetness;

    public Result<Color> setColor(Color providedColor) {
        var result = switch(name){
            case "Orange" -> Rule.validate(OrangeColorRule.class, providedColor);
            case "Strawberry" -> Rule.validate(StrawberryColorRule.class, providedColor);
            default -> Result.success(color);
        };
        if (result.isSuccess())
            color = providedColor;
        return result;
    }

    public Result<Integer> setSourness(int sourness) {
        var validationResult = Rule.validate(FruitTasteBoundsRule.class, sourness);
        if (validationResult.isSuccess())
            this.sourness = sourness;
        return validationResult;
    }

    public Result<Integer> setSweetness(int sweetness) {
        var validationResult = Rule.validate(FruitTasteBoundsRule.class, sourness);
        if (validationResult.isSuccess())
            this.sweetness = sweetness;
        return validationResult;
    }

    public static Fruit orange()
    {
        return new Fruit("Orange", Color.ORANGE, 9, 8);
    }
    public static Fruit strawberry()
    {
        return new Fruit("Strawberry", Color.RED, 5, 9);
    }

    public static Fruit banana()
    {
        return new Fruit("Banana", Color.YELLOW, 3, 7);
    }
}
