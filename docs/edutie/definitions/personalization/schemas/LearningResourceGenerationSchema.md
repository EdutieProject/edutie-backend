<!-- TOC -->
* [Learning Resource Generation Schema](#learning-resource-generation-schema)
  * [Contained data:](#contained-data)
    * [Personalization Rules](#personalization-rules)
    * [Chosen Elemental requirements](#chosen-elemental-requirements)
    * [Educator instructions](#educator-instructions)
  * [References](#references)
<!-- TOC -->

# Learning Resource Generation Schema

Learning resource generation schema is a schema with all the data required to generate Learning Resource.
This entity is read-only as it is meant only to be created and passed to the LLM to generate the learning resource.

## Contained data:

### Personalization Rules

These are the computed personalization rules. See more about personalization rules [here](../PersonalizationRule.md)

### Chosen Elemental requirements

That is the set of chosen elemental requirements that are in the scope of the LR to be generated.

### Educator instructions

These are the additional instructions given from educator.

## References

Find more information in here: https://github.com/EdutieProject/Dokumentacja/blob/main/projekty/edutie/Personalizacja.md
