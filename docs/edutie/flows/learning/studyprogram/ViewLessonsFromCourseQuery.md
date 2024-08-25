# View lessons from course query

This flow showcases the lessons from given course for the student.

## Sequence diagram

```mermaid
---
title: View Lessons
---
sequenceDiagram
    participant Client
    participant Rest API
    participant Application
    participant Domain
    participant Persistence
    autonumber
    Client ->> Rest API: View Lessons Request
    Rest API ->> Rest API: Authorize Student
    Rest API ->> Application: View Lessons Query
    Application ->> Persistence: Load lessons from course
    Persistence ->> Application: Loaded entities
    loop For each lesson
        Application ->> Domain: Verify using student's learning history<br/>if student has accomplished the lesson already
        Domain ->> Application: Lesson View
    end
    Application ->> Rest API: Wrapper result of Lesson View List
    Rest API ->> Client: List of Lesson Views response
```

## Input data

| Input           | Type            | Required |
|-----------------|-----------------|----------|
| Student User Id | UUID Identifier | ✅        |

## Description

Flow retrieves lesson views dedicated for a student sending the request.

## Lesson View

Lesson View represents the lesson together with student's performance on it.

- It contains information whether the lesson is fully "done" by the student (if all segments are already passed).