# Create dynamic learning resource flow

This flow creates personalized, dedicated learning resource for a student using a provided context that is referred in
the learning resource's contents.

*This flow is overlapping with create learning resource flow. Therefore, this doc is mostly similar to the other one.*
## Sequence diagram

```mermaid
---
title: Dynamic Learning Resource creation
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
    Client ->> Rest API: Create dynamic learning resource request
    Rest API ->> Rest API: Authorize student
    Rest API ->> Application: Create dynamic learning resource command
    Application ->> Persistence: Fetch entities
    Persistence ->> Application: Persisted entities:<br/>Student profile
    Application ->> Domain: Select Learning Requirements for student
    Domain ->> Domain: Dynamically select Learning Requirements for student
    Domain ->> Application: Selected Learning Requirements
    Application ->> Domain: Create personalized Learning Resource using<br/>dynamic LRD for the provided student
    note over Domain: The LR creation process is same as in CreateLearningResource flow
    Application ->> Persistence: Save Learning Resource
    Persistence ->> Application: Save Result
    Application ->> Rest API: Result wrapping Learning Resource
    Rest API ->> Client: Learning Resource Response

```

## Input data

| Input           | Type            | Required |
|-----------------|-----------------|----------|
| Student Id      | UUID Identifier | ✅        |
| Context         | String          | ✅        |
| Invocation Mode | Enum            | ✅        |

## Description

This flow dynamically creates a learning resource definition, which is later used to produce a dynamic learning resource. 

For now, invocation mode is by default an only mode - the random fact mode. However, it will be expanded in the future
and so will be the flow considering other invocation types. 

The L.R.G.S. creation is more widely described [here](CreateLearningResource.md)
