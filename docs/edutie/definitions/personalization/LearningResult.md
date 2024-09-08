<!-- TOC -->

* [Learning Result](#learning-result)
    * [Contained data:](#contained-data)
        * [Solution Submission (entity reference)](#solution-submission-entity-reference)
        * [Feedback](#feedback)
        * [Student reference](#student-reference)
        * [Assessments](#assessments)
    * [References](#references)

<!-- TOC -->

# Learning Result

Learning result is an entity which is responsible for student's performance indication.
It encompasses all statistics and assessments that are a result of a solution submission.

## Contained data:

### Solution Submission (entity reference)

Solution submission reference tells us which solution submission is assessed using this result

Not a contained entity.

### Feedback

Feedback is used both to communicate students how can they improve their knowledge, but it also can be used for
personalized learning resource generation.

### Student reference

Learning result contains a reference to the student it is assigned to.

### Assessments

Assessment indicate the scale of knowledge student has regarding the given learning requirement.

- An assessment is a pair of a learning requirement reference and a grade of student's knowledge regarding that
  requirement
- Learning Result may have many assessments
- Number of assessments should be equal to number of LRD learning requirements, since every Learning Requirement is
  assessed

Considering all assessments, mean grade of the learning result can be calculated.

## References

Find more information in here: https://github.com/EdutieProject/Dokumentacja/blob/main/projekty/edutie/Personalizacja.md
