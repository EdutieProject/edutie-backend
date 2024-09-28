# Get Course by Id

This flow retrieves a course details by course id

## Sequence diagram

```mermaid
---
title: Course by Id query
---
sequenceDiagram
    participant Client
    participant Rest API
    participant Application
    participant Domain
    participant Persistence
    autonumber
    Client ->> Rest API: Get Course by Id Request
    Rest API ->> Rest API: Authorize Student
    Rest API ->> Application: Get Course by Id Query
    Application ->> Persistence: Load Course
    Persistence ->> Application: Loaded entity
    Application ->> Rest API: Wrapper result of Course
    Rest API ->> Client: Course response
```

## Input data

| Input           | Type            | Required |
|-----------------|-----------------|----------|
| Student User Id | UUID Identifier | ✅        |
| Course Id       | UUID Identifier | ✅        |

## Description

Flow retrieves a course by its Id
- Flow may be invoked by any student