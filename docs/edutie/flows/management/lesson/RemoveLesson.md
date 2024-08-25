# Remove lesson flow

This flow removes a lesson. This may be performed by author educator of the lesson.

## Sequence diagram

```mermaid
---
title: Lesson removal
---
sequenceDiagram
    participant Client
    participant Rest API
    participant Application
    participant Domain
    participant Persistence
    autonumber
    Client ->> Rest API: Remove Lesson Request
    Rest API ->> Rest API: Authorize Educator
    Rest API ->> Application: Remove Lesson Command
    Application ->> Persistence: Load Data
    Persistence ->> Application: Loaded entities
    Application ->> Domain: Check if educator is author
    Domain ->> Application: true/false
    Application ->> Persistence: Remove lesson
    Persistence ->> Application: Result
    Application ->> Rest API: Result
    Rest API ->> Client: Result Response
```

## Input data

| Input            | Type        | Required |
|------------------|-------------|----------|
| Educator User Id | Educator Id | ✅        |
| Lesson Id        | Course Id   | ✅        |
| Remove Segments  | Boolean     | ❌        |

## Description

Flow removes the lesson from the application.

- Lesson itself is being removed.
    - Underlying segments stay in the database by default. They are removed if "Remove Segments" property is true
- Next lessons (if any) are automatically being connected to the previous element
- Lesson may be removed only by author educator