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

### General Feedback

General feedback summarizes the feedbacks of all the assessments made.

### Feedback type

An enumeration type symbolises the overall meaning of the feedback. Used to adjust the UI for the appropriate 
feeling of the feedback.

### Student reference

Learning result contains a reference to the student it is assigned to.

### Assessments

Assessment indicate the scale of knowledge student has regarding the given learning requirement.

Assessment encompasses:
 - The reference to the assessed learning requirement in form of the Learning Requirement Id
 - Grade of the assessment
 - Brief feedback targeting this particular learning requirement
 - Referred Sub requirements references 

Number of assessments should be equal to number of LRD learning requirements, since every Learning Requirement is assessed.

Considering all assessments, mean grade of the learning result can be calculated.

## References

Find more information in here: https://github.com/EdutieProject/Dokumentacja/blob/main/projekty/edutie/Personalizacja.md
