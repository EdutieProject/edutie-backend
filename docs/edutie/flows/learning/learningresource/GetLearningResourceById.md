# Get learning resource by Id

This flow retrieves a learning resource by its identifier

## Sequence diagram

```mermaid
---
title: Learning resource retrieval by Id
---
sequenceDiagram
    participant Client
    participant Rest API
    participant Application
    participant Persistence
    autonumber
    Client ->> Rest API: Get Learning Resource By Id Request
    Rest API ->> Rest API: Authorize Student
    Rest API ->> Application: Get Learning Resource By Id Query
    Application ->> Persistence: Load learning resource
    Persistence ->> Persistence: Find by Id
    Persistence ->> Application: Loaded entities
    Application ->> Rest API: Wrapper result of Learning Resource
    Rest API ->> Client: Learning Resource response
```

## Input data

| Input                | Type            | Required |
|----------------------|-----------------|----------|
| Student User Id      | UUID Identifier | ✅        |
| Learning Resource Id | UUID Identifier | ✅        |

## Description

Flow retrieves a learning resource querying it by its identifier.

- User invoking the flow must be a student