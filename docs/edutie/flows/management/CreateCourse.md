# Create course flow

This flow creates a course. This may be performed by any educator.

## Sequence diagram

```mermaid
---
title: Course creation
---
sequenceDiagram
    participant Client
    participant Rest API
    participant Application
    participant Domain
    participant Persistence
    autonumber
    
    Client->>Rest API: Create Course Command
    Rest API->>Rest API: Authorize Educator
    Rest API->>Application: Create Course Command
    Application->>Persistence: Load Data
    Persistence->>Application: Loaded entities
    Application->>Domain: Initialize Course with root lesson
    Domain->>Domain: Initialize first lesson with root segment
    Domain->>Application: Wrapper Result
    Application->>Persistence: Deep save course
    Persistence->>Application: Result
    Application->>Rest API: Wrapper Result
    Rest API->>Client: Response with created course
```

## Input data

| Input              | Type            | Required |
|--------------------|-----------------|----------|
| Educator User Id   | UUID Identifier | ✅        |
| Course Name        | String          | ✅        |
| Course Description | String          | ❌        |
| Science Id         | UUID Identifier | ✅        |

## Description

Flow creates a course using provided command.
- Course uses name and description provided in the command
- Course is related to the Science provided by id in the command
- Course is related to the educator profile of a user that invoked this flow. User must have educator profile to invoke this flow and that is ensured by step 2 of the sequence diagram
- Course is by default inaccessible
- Course is initialized
   - Course has the initial "root" lesson already created
   - Initial lesson has the initial "root" segment already created
6. Course is associated with the educator that created it
