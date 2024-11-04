<!-- TOC -->

* [Assessment Schema](#assessment-schema)
    * [Contained data:](#contained-data)
        * [Solution submission](#solution-submission)
        * [Assessment Problem descriptor list](#assessment-problem-descriptor-list)
    * [References](#references)

<!-- TOC -->

# Personalization Rule

Personalization rule is a fragment of personalization produced for personalization schemas. These are usually
computed in a flow during a personalization schema creation and then used by LLM to perform
personalization on the personalized resource.

## Contained data:

### Past Feedback

The past feedback, gathered from a Learning Result's [assessment](../education/LearningRequirement.md) related to the 
particular Learning Requirement.

### Personalization Rule Type

The type describes the purpose of the rule.

These are:
 - Refresh - To refresh the knowledge. Should be created from the distant assessments.
 - Reinforcement - To reinforce understanding of concepts where the learner has shown partial mastery.
 - Application - To encourage real-world application of learned knowledge.

## References

Find more information in here: https://github.com/EdutieProject/Dokumentacja/blob/main/projekty/edutie/Personalizacja.md
