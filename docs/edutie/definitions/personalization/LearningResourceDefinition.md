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
That is a many-to-many relationship. There must be at least 1 learning requirement related to the definition for it to
be functional.

Learning requirements are the primary descriptors of what the LR should require from the student. Therefore, they are
the most important when it comes to the true knowledge student exercises.

### Educator instructions

These are the additional instructions given from educator for LR generation. These encompass:
 - Theory context instructions - instructions applied for theory generation
 - Activity instructions - analogous to the previous one

## Implementations

LRD can be implemented in various ways.

### Static Learning Resource Definition

The static one is an LRD created by an Educator to serve as an absolute material definition in a study program element.

Thus, it is static, meaning that it is stored and its state can be changed because it *exists*. For instance, in the
course created by an educator.

Below - a scheme of how static LRD works in terms of LR creation.

```mermaid
---
config:
  theme: mc
  layout: elk
  look: neo
title: Static LRD utilization - Personalization version 2
---
flowchart TD
    n1["Static Learning Resource Definition"] --> n2["Learning Requirements"] & n3["Educator Instructions"]
    n4["Student"] --> n5["Past Learning Results"]
    n5 --> n6["Assessments"] & n7(["Choose qualified requirements based on the whole Learning Results"])
    n2 --> n7
    n7 --> n8["Zakwalifikowane wymagania elementarne"]
    n6 --> n9(["Create personalization rules based on past assessments of given L.Req"])
    n9 --> n10["Zasady personalizacji"]
    n8 --> n11["LR GenerationSchema"]
    n10 --> n11
    n3 --> n11
    n11 -- LLM --> n12["Learning Resource"]
    style n7 stroke-width:4px,stroke-dasharray: 5
    style n9 stroke-width:4px,stroke-dasharray: 5
```

### Dynamic Learning Resource Definition

The dynamic one is similar to the static, however it is not persisted as it is created dynamically based on dynamically
provided learning requirements. Moreover, it has no Educator instructions associated with it.

> *Schemes TBD*

## References

Find more information in here: https://github.com/EdutieProject/Dokumentacja/blob/main/projekty/edutie/Personalizacja.md
