# Get created lessons flow

This flow retrieves created lessons by an educator user. This may be performed by any educator.

## Sequence diagram

```mermaid
---
title: Authored lessons retrieval
---
sequenceDiagram
    participant Client
    participant Rest API
    participant Application
    participant Persistence
    autonumber
    Client ->> Rest API: Get Created Lessons Request
    Rest API ->> Rest API: Authorize Educator
    Rest API ->> Application: Get Created Lessons Query
    Application ->> Persistence: Load created Lessons
    Persistence ->> Persistence: Filter lessons by<br/>author Educator Id
    Persistence ->> Application: Loaded entities
    Application ->> Rest API: Wrapper result of course list
    Rest API ->> Client: List of Lessons response
```

## Input data

| Input            | Type        | Required |
|------------------|-------------|----------|
| Educator User Id | Educator Id | ✅        |

## Description

Flow retrieves created courses by given educator user.

- User invoking the flow must be an educator