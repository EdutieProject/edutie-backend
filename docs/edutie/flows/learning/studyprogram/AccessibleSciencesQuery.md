# Accessible sciences query

This flow shows accessible sciences for the student.

## Sequence diagram

```mermaid
---
title: Accessible sciences query
---
sequenceDiagram
    participant Client
    participant Rest API
    participant Application
    participant Domain
    participant Persistence
    autonumber
    Client ->> Rest API: Accessible Sciences Query Request
    Rest API ->> Rest API: Authorize Student
    Rest API ->> Application: Accessible Sciences Query
    Application ->> Persistence: Load accessible sciences
    Persistence ->> Application: Loaded entities
    Application ->> Rest API: Wrapper result of Science List
    Rest API ->> Client: List of Sciences response
```

## Input data

| Input           | Type            | Required |
|-----------------|-----------------|----------|
| Student User Id | UUID Identifier | ✅        |

## Description

Flow retrieves all accessible sciences.
