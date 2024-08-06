# Create Learning Resource Definition flow

This flow creates an LRD. This may be performed by any educator.

Note that LRD is usually created together with an encompassing entity, such as Segment. Therefore, this flow is not
the only one that can create an LRD.

## Sequence diagram

```mermaid
---
title: Learning Resource Definition creation
---
sequenceDiagram
    participant Client
    participant Rest API
    participant Application
    participant Domain
    participant Persistence
    autonumber
    
    Client->>Rest API: Create LRD Request
    Rest API->>Rest API: Authorize Educator
    Rest API->>Application: Create LRD Command
    Application->>Persistence: Load Data
    Persistence->>Application: Loaded entities
    Application->>Domain: Create LRD
    Domain->>Application: Created LRD
    Application->>Persistence: Save LRD
    Persistence->>Application: Result
    Application->>Rest API: Wrapper Result
    Rest API->>Client: Response with created LRD
```

## Input data

| Input                          | Type        | Required |
|--------------------------------|-------------|----------|
| Educator User Id               | Educator Id | ✅        |
| Theory description             | String      | ✅        |
| Exercise description           | String      | ✅        |
| Additional summary description | String      | ❌        |
| Additional hints description   | String      | ❌        |

## Description

Flow creates a Learning Resource Definition using provided command.
- Definition has the properties provided in the command
- Definition by default is not associated with any study program entity. 
- Only association of the LRD is within the author educator. 
