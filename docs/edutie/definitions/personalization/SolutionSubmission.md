<!-- TOC -->

* [Solution submission](#solution-submission)
    * [Contained data:](#contained-data)
        * [Report text](#report-text)
        * [Revealed hints count](#revealed-hints-count)
        * [Student relationship](#student-relationship)
        * [Learning Resource relationship](#learning-resource-relationship)
    * [References](#references)

<!-- TOC -->

# Solution submission

The solution submission entity represents the submitted solution of the learning resource's activity. \
Solution submission contains all data that correspond to a submitted learning resource activity.
This entity is read-only, as the submission may not be modified.

## Contained data:

### Report text

The report from the exercise solution. It should contain the thought process of the student.

### Revealed hints count

The amount of revealed hints

### Student relationship

Student relationship indicates which student submitted the relationship

### Learning Resource relationship

As the solution submission corresponds to the given learning resource, it contains the reference to it.
It is N to 1 type of relationship, meaning multiple Solution Submission can be related to one Learning Resource.

## References

No references as of now.
