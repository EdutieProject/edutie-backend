<!-- TOC -->

* [Learning Resource Generation Schema](#learning-resource-generation-schema)
    * [Contained data:](#contained-data)
        * [Chosen Elemental requirements](#chosen-elemental-requirements)
        * [Personalized Activity Details](#personalized-activity-details)
            * [Activity Personalization Rule](#activity-personalization-rule)
        * [Personalized Theory Details](#personalized-theory-details)
            * [Theory Personalization Rule](#theory-personalization-rule)
        * [Personalization Rule (Base):](#personalization-rule-base)
    * [References](#references)

<!-- TOC -->

# Learning Resource Generation Schema

Learning resource generation schema is a schema with all the data required to generate Learning Resource.
This entity is read-only as it is meant only to be created and passed to the LLM to generate the learning resource.

## Contained data:

### Chosen Elemental requirements

That is the set of chosen elemental requirements that are in the scope of the LR to be generated.

### Personalized Activity Details

Those are the activity details that are the extension of the Activity Details in the LRD but with personalization rules
included.

#### Activity Personalization Rule

This type is the extended [Personalization Rule](#personalization-rule-base) for future development regarding activity.

### Personalized Theory Details

Those are the theory details that are the extension of the Theory Details in the LRD but with personalization rules
included.

#### Theory Personalization Rule

This type is the extended [Personalization Rule](#personalization-rule-base) for future development regarding theory.

 --- 

### Personalization Rule (Base):

They are compounded of:

- Knowledge correlation (see definition [here](../education/KnowledgeCorrelation.md))
- Related assessments

The personalization rule may be extended in the future development

## References

Find more information in here: https://github.com/EdutieProject/Dokumentacja/blob/main/projekty/edutie/Personalizacja.md
