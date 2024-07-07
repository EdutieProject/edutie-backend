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
    
    Client->>Rest API: Modify Course Command
    Rest API->>Rest API: Authorize Educator
    Rest API->>Application: Modify Course Command
    Application->>Persistence: Load Data
    Persistence->>Application: Loaded entities
    loop For every command property
        Application->>Domain: Assign value to course
        Domain->>Application: Result of assignment
    end
    Application->>Persistence: Save course
    Persistence->>Application: Result
    Application->>Rest API: Result
    Rest API->>Client: Result Response
```

## Input data

| Input              | Type        | Required |
|--------------------|-------------|----------|
| Educator User Id   | Educator Id | ✅        |
| Course Id          | Course Id   | ✅        |
| Course Name        | String      | ❌        |
| Course Description | String      | ❌        |
| Accessibility      | boolean     | ❌        |

## Description

Flow updates course properties using the provided command.
 - Course being modified is the course with id provided in the command
 - Educator modifying the course must be an author of the course to modify it.
 - Properties are assigned if they are provided.
   - Property is not assigned if it is assigned null explicitly or not provided in the command (assigned null implicitly) 

