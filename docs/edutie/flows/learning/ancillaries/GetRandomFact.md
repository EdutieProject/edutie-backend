# Get Random Fact flow

This ancillary flow is responsible for providing a random fact for a student which may be somewhat
related to the latest problems regarding the past learning process.

## Sequence diagram

```mermaid
---
title: Get Random Fact Qeury
---
sequenceDiagram
    participant Client
    participant Rest API
    participant Application
    participant Domain
    participant Persistence
    participant LLM
    autonumber
    Client ->> Rest API: Get Random Fact request
    Rest API ->> Rest API: Authorize student
    Rest API ->> Application: Random Fact Query
    Application ->> LLM: Get Random Fact
    LLM ->> Application: Wrapper Result of Random Fact
    Application ->> Rest API: Result wrapping Random Fact
    Rest API ->> Client: Random Fact Response
```

## Input data

| Input                    | Type            | Required |
|--------------------------|-----------------|----------|
| Student Id               | UUID Identifier | ✅        |

## Description

Flow retrieves a random fact that should be somewhat interesting and useful in educational context.
In the future flow may be extended and the retrieved fact would be then related to past results. 