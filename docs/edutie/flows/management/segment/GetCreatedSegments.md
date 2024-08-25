# Get created lessons flow

This flow retrieves created segments by an educator user. This may be performed by any educator.

## Sequence diagram

```mermaid
---
title: Authored segments retrieval
---
sequenceDiagram
    participant Client
    participant Rest API
    participant Application
    participant Persistence
    autonumber
    Client ->> Rest API: Get Created Segments Request
    Rest API ->> Rest API: Authorize Educator
    Rest API ->> Application: Get Created Segments Query
    Application ->> Persistence: Load created Segments
    Persistence ->> Persistence: Filter lessons by<br/>author Educator Id
    Persistence ->> Application: Loaded entities
    Application ->> Rest API: Wrapper result of segments list
    Rest API ->> Client: List of segments response
```

## Input data

| Input            | Type        | Required |
|------------------|-------------|----------|
| Educator User Id | Educator Id | ✅        |

## Description

Flow retrieves created segments by given educator user.

- User invoking the flow must be an educator