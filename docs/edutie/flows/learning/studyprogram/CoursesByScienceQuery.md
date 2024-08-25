# Courses by Science query

This flow shows courses that correspond to the given science

## Sequence diagram

```mermaid
---
title: Courses by Science query
---
sequenceDiagram
    participant Client
    participant Rest API
    participant Application
    participant Domain
    participant Persistence
    autonumber
    Client ->> Rest API: Courses by Science Query Request
    Rest API ->> Rest API: Authorize Student
    Rest API ->> Application: Courses by Science Query
    Application ->> Persistence: Load Courses related to given Science & accessible
    Persistence ->> Application: Loaded entities
    Application ->> Rest API: Wrapper result of Course List
    Rest API ->> Client: List of Courses response
```

## Input data

| Input           | Type            | Required |
|-----------------|-----------------|----------|
| Student User Id | UUID Identifier | ✅        |
| Science Id      | UUID Identifier | ✅        |

## Description

Flow retrieves all accessible courses corresponding to given science
