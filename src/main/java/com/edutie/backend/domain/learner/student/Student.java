package com.edutie.backend.domain.learner.student;

import com.edutie.backend.domain.common.Utilities;
import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.learner.learningresult.LearningResult;
import com.edutie.backend.domain.learner.student.entites.AbilityLearningParameter;
import com.edutie.backend.domain.learner.student.entites.IntelligenceLearningParameter;
import com.edutie.backend.domain.learner.student.entites.base.LearningParameter;
import com.edutie.backend.domain.learner.student.enums.SchoolType;
import com.edutie.backend.domain.learner.student.errors.StudentErrors;
import com.edutie.backend.domain.learner.student.identities.LearningParameterId;
import com.edutie.backend.domain.learner.student.identities.StudentId;
import com.edutie.backend.domain.learner.student.rules.SchoolGradeNumberRule;
import com.edutie.backend.domain.learner.student.rules.StudentAgeBoundsRule;
import com.edutie.backend.domain.learner.student.valueobjects.SchoolStage;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import validation.Result;
import validation.RuleEnforcer;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Student class conceals all the student characteristics of the user.
 * Note that the relationship with the user is maintained by createdBy field in the base class.
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
public class Student extends AuditableEntityBase<StudentId> {
    @Embedded
    private SchoolStage schoolStage = new SchoolStage();
    private LocalDate birthdate = null;
    @OneToMany(targetEntity = AbilityLearningParameter.class, fetch = FetchType.EAGER)
    private final Set<AbilityLearningParameter> abilityLearningParameters = new HashSet<>();
    @OneToMany(targetEntity = IntelligenceLearningParameter.class, fetch = FetchType.EAGER)
    private final Set<IntelligenceLearningParameter> intelligenceLearningParameters = new HashSet<>();
    @OneToMany(mappedBy = "student_id")
    private final Set<LearningResult> learningHistory = new HashSet<>();

    /**
     * Default constructor used for associating student's account with a user.
     * Initializes all fields to default values. This constructor should be used
     * by the application services instead of no-args constructor because there
     * is no possibility to bond student entity to a user later.
     *
     * @param userId user's identity
     */
    public static Student create(UserId userId) {
        Student student = new Student();
        student.setCreatedBy(userId);
        student.setId(new StudentId());
        return student;
    }

    /**
     * Adapts learning parameters based on provided progress value. May throw a runtime
     * exception if the provided trait class is not valid - there is no tracker
     * associated with it.
     *
     * @param learningParamClass class of trait
     * @param trait              concrete trait
     * @param progressValue      progress measured as double. May be negative
     * @param <T>                type of trait
     */
    @SneakyThrows
    public <T extends Enum<T>, U extends LearningParameter<T>> void adaptLearningParameters(Class<U> learningParamClass, T trait, double progressValue) {
        Set<U> learningParameters = Utilities.findSetOf(learningParamClass, this).orElseThrow();
        var searchedLearningParam = learningParameters.stream().filter(o -> o.getTrait() == trait).findFirst();
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
     *
     * @param learningParamClass learning parameter class
     * @param trait              searched trait
     * @param <T>                type of trait
     * @param <U>                type of learning parameter
     * @return learning param as optional. Empty optional if parameter has not been initialized
     */
    public <T extends Enum<T>, U extends LearningParameter<T>> Optional<U> getLearningParameter(Class<U> learningParamClass, T trait) {
        return Utilities.findSetOf(learningParamClass, this)
                .orElseThrow()
                .stream()
                .filter(o -> o.getTrait() == trait)
                .findFirst();
    }

    /**
     * Returns value of given learning parameter. If the parameter has not been initialized, returns
     * 0.0 instead.
     *
     * @param learningParamClass class of learning parameter
     * @param trait              searched trait
     * @param <T>                type of trait
     * @param <U>                type of learning parameter
     * @return value of learning parameter as double. 0.0 if absent.
     */
    public <T extends Enum<T>, U extends LearningParameter<T>> double getLearningParameterValue(Class<U> learningParamClass, T trait) {
        var learningParam = getLearningParameter(learningParamClass, trait);
        return learningParam.map(LearningParameter::getValue).orElse(0.0);
    }

    public Set<LearningParameter<?>> getAllLearningParameters() {
        Set<LearningParameter<?>> learningParameters = new HashSet<>();
        learningParameters.addAll(abilityLearningParameters);
        learningParameters.addAll(intelligenceLearningParameters);
        return learningParameters;
    }


    public Result changeSchoolStage(int progressValue) {
        if (schoolStage == null) return Result.failure(
                StudentErrors.schoolStageAlternationError()
        );

        var newSchoolStage = new SchoolStage(
                schoolStage.schoolType(),
                schoolStage.gradeNumber() + progressValue
        );

        return setSchoolStage(newSchoolStage);
    }


    public Result setSchoolStage(SchoolStage providedSchoolStage) {
        var validationResult = RuleEnforcer.validate(SchoolGradeNumberRule.class, providedSchoolStage);
        if (validationResult.isSuccess())
            schoolStage = providedSchoolStage;
        return validationResult;
    }

    /**
     * Sets student school stage based on verbose School Type and grade Number parameters
     *
     * @param schoolType  type of student's school
     * @param gradeNumber number of the grade
     */
    public Result setSchoolStage(SchoolType schoolType, int gradeNumber) {
        SchoolStage schoolStage = new SchoolStage(schoolType, gradeNumber);
        return setSchoolStage(schoolStage);
    }

    /**
     * Sets student school stage based on details
     *
     * @param schoolType     school type
     * @param gradeNumber    grade number
     * @param specialization class specialization as string
     * @return Result
     */
    public Result setSchoolStage(SchoolType schoolType, int gradeNumber, String specialization) {
        SchoolStage schoolStage = new SchoolStage(schoolType, gradeNumber, specialization);
        return setSchoolStage(schoolStage);
    }

    /**
     * Sets student birthdate
     *
     * @param providedBirthdate a birthdate to be set
     * @return Rule validation result
     */
    public Result setBirthdate(LocalDate providedBirthdate) {
        var validationResult = RuleEnforcer.validate(StudentAgeBoundsRule.class, providedBirthdate);
        if (validationResult.isSuccess())
            birthdate = providedBirthdate;
        return validationResult;
    }

}
