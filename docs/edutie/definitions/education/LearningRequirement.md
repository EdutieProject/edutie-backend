<!-- TOC -->

* [Learning Requirement](#learning-requirement)
    * [Contained data:](#contained-data)
        * [Name](#name)
        * [Description](#description)
        * [Sub requirements](#sub-requirements)
        * [Knowledge Subject Id](#knowledge-subject-id)
    * [References](#references)

<!-- TOC -->

# Learning Requirement

Knowledge Correlation is an entity indicating of learning requirement's knowledge subject (wiki page) relation to other
knowledge subjects.
It does not occur in the singular form. It is used as a response for getting knowledge correlations using given
knowledge subject id.

## Contained data:

### Name

The name of the requirement, used both as displayable name and as a prompt

### Description

Description used as a prompt regarding learning resource creation when the definition includes this requirement.

### Sub requirements

Sub requirements are the points that represent steps that students must pass to fully satisfy the
requirement. Sub requirement's order is important, as the first one is taken as the easiest one and the last
one is taken as the most difficult.

### Knowledge Subject Id

The identity of correlated knowledge subject, which for now is a wikipedia article. The knowledge subject
is an entity persisted in the external Knowledge Map service. Knowledge map service can give us information about
correlations between knowledge subject, therefore we can correlate learning requirements as the relation between
the requirement and knowledge subject is one-to-one.

## References

Find more information in here: https://github.com/EdutieProject/Dokumentacja/blob/main/projekty/edutie/Edukacja.md
