<!-- TOC -->
* [Learning Resource Definition](#learning-resource-definition)
  * [Contained data:](#contained-data)
    * [Learning requirements relationships](#learning-requirements-relationships)
    * [Theory context sources](#theory-context-sources)
    * [Activity context sources](#activity-context-sources)
  * [References](#references)
<!-- TOC -->

# Learning Resource Definition

Learning resource definition is a scheme from which personalized Learning Resource can be created.

## Contained data:

### Learning requirements relationships
Learning Resource Definition has access to Learning Requirements references. 
That is a many-to-many relationship. There must be at least 1 learning requirement related to the definition.

### Theory context sources
Learning Resource Definition has a theory context source. 
The theory context source describes how the theory context in the learning resource should look like.
The contents of this part are:
 - theory description - a description used for theory context generation
 - theory summary additions - an additional prompt used for summary generation

### Activity context sources
Learning Resource Definition has an activity context source.
Activity context source describes how the activity for the learning resource should look like. 
The contents of this part are:
 - activity description - a description used for task generation
 - hints description additions - an additional description used for hint generation

## References
Find more information on LRD in here: https://github.com/EdutieProject/Dokumentacja/blob/main/projekty/edutie/Personalizacja.md