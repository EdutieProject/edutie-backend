# Remove course flow

This flow removes a course. This may be performed by author educator of the course.

## Sequence diagram

```mermaid
---
title: Course removal
---
sequenceDiagram
    participant Client
    participant Rest API
    participant Application
    participant Domain
    participant Persistence
    autonumber
    
    Client->>Rest API: Remove Course Request
    Rest API->>Rest API: Authorize Educator
    Rest API->>Application: Remove Course Command
    Application->>Persistence: Load Data
    Persistence->>Application: Loaded entities
    Application->>Domain: Check if educator is author
    Domain->>Application: true/false
    Application->>Persistence: Remove course
    Persistence->>Application: Result
    Application->>Rest API: Result
    Rest API->>Client: Result Response
```

## Input data

| Input              | Type        | Required |
|--------------------|-------------|----------|
| Educator User Id   | Educator Id | ✅        |
| Course Id          | Course Id   | ✅        |

## Description

Flow removes the course from the application.
 - Only course itself is being removed.
   - Underlying lessons & segments stay in the database.
 - Course may be removed only by author educator