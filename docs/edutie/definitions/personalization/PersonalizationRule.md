<!-- TOC -->
* [Personalization Rule](#personalization-rule)
  * [Implementation note](#implementation-note)
  * [Contained data:](#contained-data)
    * [Context](#context)
    * [Personalization Type](#personalization-type)
  * [Sample implementation - Remediation rule:](#sample-implementation---remediation-rule)
    * [Context - Past Feedback](#context---past-feedback)
    * [Personalization Type - Remediation](#personalization-type---remediation)
  * [References](#references)
<!-- TOC -->

# Personalization Rule

Personalization rule is a fragment of personalization produced for personalization schemas. These are usually
computed as a result of [personalization strategy](./PersonalizationStrategy.md) qualification.

## Implementation note

The implementation follows a convention that a concrete class is named like: *PersonalizationType*Rule
naming pattern. Following this pattern is required for [personalization type](#personalization-type) to work.

## Contained data:

### Context

That is the personalization context that will be later used by the personalization technology, e.g. LLM. In this case,
the LLM should have the way of handling the context hardcoded by the personalization type.

### Personalization Type

That is not the contained data field, instead the personalization type is coded inside the implementation class name.

The personalization type should be interpreted as an information "code" for the LLM telling it how to utilize
the context in the personalization purpose.

## Sample implementation - Remediation rule:

### Context - Past Feedback

The past feedback, gathered from a Learning Result's [assessment](../education/LearningRequirement.md) related to the 
particular Learning Requirement. Used for remediation, as the remediation is the personalization type.

### Personalization Type - Remediation

As the class name is `FeedbackRemediationRule` the inferred personalization type is *FeedbackRemediation* - meaning
that the past feedback will be used by the LLM as the context for remediating certain subject that student has weak
understanding of.

## References

Find more information in here: https://github.com/EdutieProject/Dokumentacja/blob/main/projekty/edutie/Personalizacja.md
