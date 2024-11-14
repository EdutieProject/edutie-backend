<!-- TOC -->
* [Personalization Strategy](#personalization-strategy)
  * [What does it do?](#what-does-it-do)
    * [Rule Qualification](#rule-qualification)
    * [Priority](#priority)
  * [Engine](#engine)
  * [Concrete implementations](#concrete-implementations)
    * [Feedback Remediation Strategy](#feedback-remediation-strategy)
      * [Goal](#goal)
      * [Description & Criteria](#description--criteria)
    * [Familiar Remediation Strategy](#familiar-remediation-strategy)
      * [Goal](#goal-1)
      * [Description & Criteria](#description--criteria-1)
    * [Recommendation Strategy](#recommendation-strategy)
      * [Goal](#goal-2)
      * [Description](#description)
    * [Refresh strategy](#refresh-strategy)
      * [Goal](#goal-3)
      * [Description & Criteria](#description--criteria-2)
  * [References](#references)
<!-- TOC -->

# Personalization Strategy

Personalization Strategy is a singular strategy that can be chosen to personalize the learning resource.
Personalization strategies can be qualified into the LR personalization schema, producing
a [Personalization Rule](./PersonalizationRule.md)
for the strategy.

## What does it do?

### Rule Qualification

Qualifies the rule of the given strategy. To be more exact, the qualification process
checks whether the appropriate criteria have been met, in order to assign the personalization rule
to the learning resource being created.

### Priority

The integer that signifies the priority of the strategy in the qualification process. The bigger the integer,
the more priority strategy has. One can also pass low integers to make the priority lower.

The default is 0, so if the strategy is below the priority "baseline" one could set it to -1 so the strategy
becomes less important.

## Engine

The strategies are managed using the engine which decides which strategies are qualified using their qualification
criteria
and their priority.

## Concrete implementations

Below you can find a list of concrete implementations of the strategies used in Edutie.

### Feedback Remediation Strategy

#### Goal

The goal here is to create the learning resource knowing about the feedback gained in the previous learning process

#### Description & Criteria

We consider only the results of the learning requirements that are being used to create
the [Learning Resource](./LearningResource.md).
If the latest results have weak performance and there is little difficulty progress,
we qualify a rule considering the most recent feedback.

If the performance is good, we do not qualify a rule.

### Familiar Remediation Strategy

#### Goal

The goal is to explain and adjust the learning resource for the student knowing about his strengths
in the other, strongly correlated, subject.

#### Description & Criteria

The strategy searches for learning streaks in the latest learning streak which is of the learning requirement
that is strongly correlated with the learning requirements of the definition being used.

### Recommendation Strategy

#### Goal

The goal is to suggest a similar subject for a student that performs well in the strongly correlated learning
requirement.

#### Description

The strategy checks if student is in the learning streak and performs well, ramping up the difficulty.
If so, strategy uses knowledge map to search for a similar subject and creates a rule for it.

### Refresh strategy

#### Goal

The goal of this strategy is to remind student of the learning that they have made in the past.

#### Description & Criteria

Refresh strategy searches for learning streaks that have been made in the distant past (more exactly, searches for
consecutive [Learning Results](./LearningResult.md) which all have at least
one [Learning Requirement](../education/LearningRequirement.md)
in common). It selects a number of these streaks that are the most distant.

Next, it chooses the most correlated one and qualifies the elemental requirement using
the usual qualification process.

The streaks are chosen considering the performance. The performance of the considered results must be at least good,
because if it is not then the learning requirement is not grasped yet and reminding is pointless.

## References

Find more information in here: https://github.com/EdutieProject/Dokumentacja/blob/main/projekty/edutie/Personalizacja.md
