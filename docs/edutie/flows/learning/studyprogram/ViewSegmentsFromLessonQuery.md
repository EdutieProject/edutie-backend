# View segments from course query

This flow showcases the lessons from given course for the student.

## Sequence diagram

```mermaid
---
title: View Segments
---
sequenceDiagram
    participant Client
    participant Rest API
    participant Application
    participant Domain
    participant Persistence
    autonumber
    Client ->> Rest API: View Segments Request
    Rest API ->> Rest API: Authorize Student
    Rest API ->> Application: View Segments Query
    Application ->> Persistence: Load segments from course
    Persistence ->> Application: Loaded entities
    loop For each lesson
        Application ->> Domain: Gather the personalized data from student's<br/>learning history
        Domain ->> Application: Segment View
    end
    Application ->> Rest API: Wrapper result of Segment View List
    Rest API ->> Client: List of Segment Views response
```

## Input data

| Input           | Type            | Required |
|-----------------|-----------------|----------|
| Student User Id | UUID Identifier | ✅        |

## Description

Flow retrieves segment views dedicated for a student sending the request.

## Segment View

Segment View represents the lesson together with student's performance on it. It contains information:

- How many approaches student had on this segment (different LRs)
- How many times did he pass the segment
- What is the current difficulty that would be utilized for the next LR