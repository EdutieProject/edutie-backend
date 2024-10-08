# Create random fact dynamic learning resource flow

This flow creates personalized, dedicated learning resource for a student using a random curious fact potentially obtained
previously in the ancillary random fact flow.

*This flow is overlapping with create learning resource flow. Therefore, this doc is mostly similar to the other one. This problem
shall be solved in the future under [this GH issue](https://github.com/EdutieProject/edutie-backend/issues/217)*

## Sequence diagram

```mermaid
---
title: Random fact Dynamic Learning Resource creation
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
    Client ->> Rest API: Create R.F. Dynamic Learning Resource request
    Rest API ->> Rest API: Authorize student
    Rest API ->> Application: Create R.F. Dynamic Learning Resource command
    Application ->> Persistence: Fetch entities
    Persistence ->> Application: Persisted entities:<br/>Student profile<br/>+ Latest Learning Results
    Application ->> Domain: Create Learning Resource Definition based on<br/>latest results and random fact
    Domain ->> Application: Learning Resource Definition
    Application ->> Domain: Create Learning Resource Domain Service
    note left of Domain: First, create an LRGS
    loop For every learning requirement in LRD
        Domain ->> Domain: Create Problem descriptor
        Domain ->> Wikimap: Get knowledge correlations
        Wikimap ->> Domain: Knowledge correlations
        loop For every knowledge correlation
            Domain ->> Domain: Create personalization rule
        end
        Domain ->> Domain: Calculate qualified sub-requirements
    end
    note left of Domain: Now, let LLM generate LR from LRGS
    Domain ->> LLM: Learning Resource Generation Schema
    LLM ->> Domain: Learning Resource
    Domain ->> Application: Learning Resource
    Application ->> Persistence: Save Learning Resource
    Persistence ->> Application: Save Result
    Application ->> Rest API: Result wrapping Learning Resource
    Rest API ->> Client: Learning Resource Response

```

## Input data

| Input       | Type            | Required |
|-------------|-----------------|----------|
| Student Id  | UUID Identifier | ✅        |
| Random Fact | String          | ✅        |

## Description

This flow dynamically creates a learning resource definition, which is later used to produce a learning resource. 

As of now, the dynamic LRD is created just in a way that the Learning Requirements are copied from the latest Learning
Result. Thus, the LRD is just a middleman in producing a similar Learning Resource to the latest one but accounting for
the random fact that may have made the student interested.

The L.R.G.S. creation is more widely described [here](CreateLearningResource.md)
