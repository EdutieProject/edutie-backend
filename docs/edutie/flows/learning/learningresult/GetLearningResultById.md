# Get Learning Result by Id

This flow retrieves a learning result by its unique identifier

## Sequence diagram

```mermaid
---
title: Learning Result retrieval by Id
---
sequenceDiagram
    participant Client
    participant Rest API
    participant Application
    participant Persistence
    autonumber
    Client ->> Rest API: Get Learning Result By Id Request
    Rest API ->> Rest API: Authorize Student
    Rest API ->> Application: Get Learning Result By Id Query
    Application ->> Persistence: Load Learning Result
    Persistence ->> Persistence: Find by Id
    Persistence ->> Application: Loaded entity
    Application ->> Rest API: Wrapper result of Learning Result
    Rest API ->> Client: Learning Result response
```

## Input data

| Input              | Type            | Required |
|--------------------|-----------------|----------|
| Student User Id    | UUID Identifier | ✅        |
| Learning Result Id | UUID Identifier | ✅        |

## Description

Flow retrieves a learning result querying it by its unique identifier.

- User invoking the flow must be a student