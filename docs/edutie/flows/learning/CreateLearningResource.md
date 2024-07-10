# Create learning resource flow
This flow creates personalized, dedicated learning resource for a student.

## Sequence diagram

```mermaid
---
title: Course creation
---
sequenceDiagram
    participant Client
    participant Rest API
    participant Application
    participant Domain
    participant Persistence
    participant Wikimap
    participant LLM
    autonumber
    
    Client->>Rest API: Create learning resource request
    Rest API->>Rest API: Authorize student
    Rest API->>Application: Create learning resource command
    Application->>Persistence: Fetch entities
    Persistence->>Application: Persisted entities:<br/>Learning Resource Definition<br/>Student profile
    loop For every learning requirement in LRD
        Application->>Wikimap: Get knowledge correlations
        Wikimap->>Application: Knowledge correlations
    end
    Application->>Domain: Create LR Generation Schema using:<br/> LR Definition,<br/>Student profile (learning history) <br/>and Knowledge Correlations
    loop For every knowledge correlation
      Domain->>Domain: Create personalization rule
    end
    Domain->>Application: Learning Resource Generation Schema
    Application->>LLM: Request Learning Resource using LRGS
    LLM->>Application: Learning Resource LLM View (DTO)
    Application->>Domain: Create Learning Resource using LLM View
    Domain->>Application: Learning Resource
    Application->>Persistence: Save Learning Resource
    Persistence->>Application: Save Result
    Application->>Rest API: Result wrapping Learning Resource
    Rest API->>Client: Learning Resource Response

```

## Input data

| Input                           | Type                            | Required |
|---------------------------------|---------------------------------|----------|
| Student Id                      | Student Id                      | ✅        |
| Learning Resource Definition Id | Learning Resource Definition Id | ✅        |

## Description
This flow creates a learning resource. It is created using learning resource definition and a student profile.

Learning resource is created by constructing Learning Resource Generation Schema.
Let's describe the algorithm behind the LRGS creation:
1. Input data: Student id and Learning Resource Definition id
2. Load the data - student profile and LR Definition
3. For each Learning Requirement in LR Definition:
   1. Get Knowledge Subject Id from learning requirement
   2. Using Knowledge Subject Id, retrieve Knowledge Correlations from Wikimap
   3. For each knowledge correlation create Personalization Rule
      - using the knowledge correlation (spread properties)
      - using the student's learning history learning results
      - calculating qualified sub-requirements
4. Using the created Personalization Rules and the LR Definition create Learning Resource Generation Schema

The LRGS is sent to the LLM and the response is being restructured to match Learning Resource.