package com.edutie.edutiebackend.domain.core.skill.errors;

import com.edutie.edutiebackend.domain.rule.RuleError;

public class SkillErrors {
    public static RuleError unhandledTraitError()
    {
        return new RuleError("UnsupportedTraitProvided", "Provided trait is not handled by this activity");
    }
}
