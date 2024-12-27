# Get Solution Submission by Id

This flow retrieves a solution submission by its unique identifier

## Sequence diagram

```mermaid
---
title: Get Solution Submission by Id
---
sequenceDiagram
    participant Client
    participant Rest API
    participant Application
    participant Persistence
    autonumber
    Client ->> Rest API: Get Solution Submission by Id Request
    Rest API ->> Rest API: Authorize Student
    Rest API ->> Application: Get Solution Submission by Id Query
    Application ->> Persistence: Load entity
    Persistence ->> Persistence: Find by Id
    Persistence ->> Application: Loaded entity
    Application ->> Rest API: Wrapper result of Solution Submission
    Rest API ->> Client: Solution Submission response
```

## Input data

| Input                  | Type            | Required |
|------------------------|-----------------|----------|
| Student User Id        | UUID Identifier | ✅        |
| Solution Submission Id | UUID Identifier | ✅        |

## Description

Flow retrieves a solution submission querying it by its unique identifier.

- User invoking the flow must be a student