# Create dynamic learning resource flow

This flow creates personalized, dedicated learning resource for a student using another learning resource id. The flow
is created either by using the same definition. Therefore, the flow does not support dynamic Learning Resources.

## Sequence diagram

```mermaid
---
title: Create Similar Learning Resource
---
sequenceDiagram
    participant Client
    participant Rest API
    participant Application
    participant Domain
    participant Persistence
    participant Knowledge Map
    participant LLM
    autonumber
    Client ->> Rest API: Create similar learning resource request
    Rest API ->> Rest API: Authorize student
    Rest API ->> Application: Create similar learning resource command
    Application ->> Persistence: Fetch entities
    Persistence ->> Application: Persisted entities:<br/>Student profile<br/>Learning Resource Definition
    opt No Static LRD associated
        Application ->> Rest API: If there is no LRD associated, end flow.
        Rest API ->> Client: Unsuccessful Response
    end
    note over Client, LLM: Rest of the flow goes same as in CreateLearningResource flow

```

## Input data

| Input                | Type            | Required |
|----------------------|-----------------|----------|
| Student Id           | UUID Identifier | ✅        |
| Learning Resource Id | UUID Identifier | ✅        |

## Description

This flow creates a similar learning resource to the one which identifier was provided as an argument. Due to the fact
that dynamic definitions are not persisted, the similar creation process supports only LRs created by static definitions.
