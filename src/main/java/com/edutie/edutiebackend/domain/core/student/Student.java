package com.edutie.edutiebackend.domain.core.student;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.edutie.edutiebackend.domain.core.common.base.AuditableEntityBase;
import com.edutie.edutiebackend.domain.core.common.identities.UserId;
import com.edutie.edutiebackend.domain.core.student.entites.AbilityLearningParameter;
import com.edutie.edutiebackend.domain.core.student.entites.IntelligenceLearningParameter;
import com.edutie.edutiebackend.domain.core.student.entites.base.LearningParameter;
import com.edutie.edutiebackend.domain.core.student.identities.LearningParameterId;
import com.edutie.edutiebackend.domain.rule.Result;
import com.edutie.edutiebackend.domain.rule.Rule;
import com.edutie.edutiebackend.domain.core.student.enums.SchoolType;
import com.edutie.edutiebackend.domain.core.student.errors.StudentErrors;
import com.edutie.edutiebackend.domain.core.student.identities.StudentId;
import com.edutie.edutiebackend.domain.core.student.rules.StudentAgeBoundsRule;
import com.edutie.edutiebackend.domain.core.student.rules.SchoolGradeNumberRule;
import com.edutie.edutiebackend.domain.core.student.valueobjects.SchoolStage;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

/**
 * Student class conceals all the student characteristics of the user.
 * This is an aggregate root of the student.
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Student extends AuditableEntityBase<StudentId> {
    @Nullable
    @Getter
    private SchoolStage schoolStage = null;
    @Nullable
    @Getter
    private LocalDate birthdate = null;
    Set<AbilityLearningParameter> abilityLearningParameters = new HashSet<>();
    Set<IntelligenceLearningParameter> intelligenceLearningParameters = new HashSet<>();

    @Getter
    // one-to-one relationship with user
    private UserId userId;

    /**
     * Default constructor used for associating student's account with a user.
     * Initializes all fields to default values. This constructor should be used
     * by the application services instead of no-args constructor because there
     * is no possibility to bond student entity to a user later.
     * @param userId user's identity
     */
    public Student(UserId userId)
    {
        this.userId = userId;
    }

    /**
     * Adapts learning parameters based on provided progress value. May throw a runtime
     * exception if the provided trait class is not valid - there is no tracker
     * associated with it.
     * @param learningParamClass class of trait
     * @param trait concrete trait
     * @param progressValue progress measured as double. May be negative
     * @param <T> type of trait
     */
    @SneakyThrows
    public <T extends Enum<T>, U extends LearningParameter<T>> void adaptLearningParameters(Class<U> learningParamClass, T trait, double progressValue)
    {
        Set<U> learningParameters = findSetOf(learningParamClass).orElseThrow();
        var searchedLearningParam = learningParameters.stream().filter(o->o.getTrait() == trait).findFirst();
        if (searchedLearningParam.isPresent()) searchedLearningParam.get().adapt(progressValue);
        else {
            var newLearningParam = learningParamClass.getConstructor().newInstance();
            newLearningParam.setId(new LearningParameterId());
            newLearningParam.setTrait(trait);
            newLearningParam.setValue(progressValue);
            learningParameters.add(newLearningParam);
        }
    }

    /**
     * Function retrieving concrete learning parameter.
     * @param learningParamClass learning parameter class
     * @param trait searched trait
     * @return learning param as optional. Empty optional if parameter has not been initialized
     * @param <T> type of trait
     * @param <U> type of learning parameter
     */
    public <T extends Enum<T>, U extends LearningParameter<T>> Optional<U> getLearningParameter(Class<U> learningParamClass, T trait)
    {
        return findSetOf(learningParamClass)
                .orElseThrow()
                .stream()
                .filter(o->o.getTrait()==trait)
                .findFirst();
    }

    /**
     * Returns value of given learning parameter. If the parameter has not been initialized, returns
     * 0.0 instead.
     * @param learningParamClass class of learning parameter
     * @param trait searched trait
     * @return value of learning parameter as double. 0.0 if absent.
     * @param <T> type of trait
     * @param <U> type of learning parameter
     */
    public <T extends Enum<T>, U extends LearningParameter<T>> double getLearningParameterValue(Class<U> learningParamClass, T trait)
    {
        var learningParam = getLearningParameter(learningParamClass, trait);
        return learningParam.map(LearningParameter::getValue).orElse(0.0);
    }

    public <U extends  LearningParameter<?>> Set<U> getLearningParameters(Class<U> learningParamClass)
    {
        return findSetOf(learningParamClass).orElseThrow();
    }


    public Result changeSchoolStage(int progressValue){
        if (schoolStage == null) return Result.failure(
                StudentErrors.schoolStageAlternationError()
        );

        var newSchoolStage = new SchoolStage(
                schoolStage.schoolType(),
                schoolStage.gradeNumber() + progressValue
        );

        return setSchoolStage(newSchoolStage);
    }


    public Result setSchoolStage(SchoolStage providedSchoolStage){
        var validationResult = Rule.validate(SchoolGradeNumberRule.class, providedSchoolStage);
        if (validationResult.isSuccess())
            schoolStage = providedSchoolStage;
        return validationResult;
    }

    /**
     * Sets student school stage based on verbose School Type and grade Number parameters
     * @param schoolType type of student's school
     * @param gradeNumber number of the grade
     */
    public Result setSchoolStage(SchoolType schoolType, int gradeNumber){
        SchoolStage schoolStage = new SchoolStage(schoolType, gradeNumber);
        return setSchoolStage(schoolStage);
    }


    /**
     * Sets student birthdate
     * @param providedBirthdate a birthdate to be set
     * @return Rule validation result
     */
    public Result setBirthdate(LocalDate providedBirthdate){
        var validationResult = Rule.validate(StudentAgeBoundsRule.class, providedBirthdate);
        if(validationResult.isSuccess())
            birthdate = providedBirthdate;
        return validationResult;
    }


    private <T> Optional<Set<T>> findSetOf(Class<T> traitClass) {
        Field[] fields = getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.getType() == Set.class) {
                ParameterizedType genericType = (ParameterizedType) field.getGenericType();
                Type[] typeArguments = genericType.getActualTypeArguments();

                if (typeArguments.length > 0 && typeArguments[0] == traitClass) {
                    try {
                        field.setAccessible(true);
                        return Optional.of((Set<T>) field.get(this));
                    } catch (IllegalAccessException e) {
                        System.out.println(e.getMessage());
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return Optional.empty();
    }
}
