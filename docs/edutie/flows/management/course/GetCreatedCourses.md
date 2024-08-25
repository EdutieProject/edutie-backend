# Get created course flow

This flow retrieves created courses by an educator user. This may be performed by any educator.

## Sequence diagram

```mermaid
---
title: Authored courses retrieval
---
sequenceDiagram
    participant Client
    participant Rest API
    participant Application
    participant Persistence
    autonumber
    Client ->> Rest API: Get Created Courses Request
    Rest API ->> Rest API: Authorize Educator
    Rest API ->> Application: Get Created Courses Query
    Application ->> Persistence: Load created courses
    Persistence ->> Persistence: Filter courses by<br/>author Educator Id
    Persistence ->> Application: Loaded entities
    Application ->> Rest API: Wrapper result of course list
    Rest API ->> Client: List of courses response
```

## Input data

| Input            | Type            | Required |
|------------------|-----------------|----------|
| Educator User Id | UUID Identifier | ✅        |

## Description

Flow retrieves created courses by given educator user.

- User invoking the flow must be an educator
- Courses retrieved are both accessible and inaccessible. Thus, all courses authored by user are retrieved.