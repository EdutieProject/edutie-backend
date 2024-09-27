# Get learning resources by definition Id

This flow retrieves a learning resources by definition Id

## Sequence diagram

```mermaid
---
title: Learning resources retrieval by definition Id
---
sequenceDiagram
    participant Client
    participant Rest API
    participant Application
    participant Persistence
    autonumber
    Client ->> Rest API: Get Learning Resources By Definition Id Request
    Rest API ->> Rest API: Authorize Student
    Rest API ->> Application: Get Learning Resources By Definition Id Query
    Application ->> Persistence: Load learning resources
    Persistence ->> Persistence: Load definition
    Persistence ->> Persistence: Find LRs by definition
    Persistence ->> Application: Loaded entities
    Application ->> Rest API: Wrapper result of Learning Resources
    Rest API ->> Client: List of Learning Resources response
```

## Input data

| Input                           | Type            | Required |
|---------------------------------|-----------------|----------|
| Student User Id                 | UUID Identifier | ✅        |
| Learning Resource Definition Id | UUID Identifier | ✅        |

## Description

Flow retrieves a learning resource querying it by associated Learning Resource Definition identifier.

- User invoking the flow must be a student