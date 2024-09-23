# Get latest Learning Results

This flow retrieves latest learning results created by student invoking the flow.

## Sequence diagram

```mermaid
---
title: Retrieve latest learning results flow
---
sequenceDiagram
    participant Client
    participant Rest API
    participant Application
    participant Persistence
    autonumber
    Client ->> Rest API: Get Latest Learning Results Request
    Rest API ->> Rest API: Authorize Student
    Rest API ->> Application: Get Latest Learning Results Query
    Application ->> Persistence: Load learning results
    Persistence ->> Persistence: Filter results for student<br/>Order by creation date<br/>Get given amount and/or limit by the date
    Persistence ->> Application: Loaded entities
    Application ->> Rest API: Wrapper result of Learning Result List
    Rest API ->> Client: List of Learning Results response
```

## Input data

| Input           | Type            | Required |
|-----------------|-----------------|----------|
| Student User Id | UUID Identifier | ✅        |
| Amount          | Integer         | ❌        |
| Maximum Date    | ISO Date        | ❌        |

## Description

Flow retrieves latest learning results created by student's submissions.

- User invoking the flow must be a student
- Amount defaults to 5 if not provided
- If the maximum date is provided and no amount is set, all the results up to the provided date are provided.
- If both the maximum date and the amount is provided, flow returns learning results up to the maximum date that may be limited to certain amount.
- The flow with no request parameters is considered as a flow retrieving latest 5 learning results.