# Create random fact dynamic learning resource flow

This flow creates personalized, dedicated learning resource for a student using a random curious fact potentially obtained
previously in the ancillary random fact flow.

*This flow is overlapping with create learning resource flow. Therefore, this doc is mostly similar to the other one. This problem
shall be solved in the future under [this GH issue](https://github.com/EdutieProject/edutie-backend/issues/217)*

## Sequence diagram

```mermaid
---
title: Dynamic Learning Resource creation - Random fact variant
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
    Client ->> Rest API: Create learning resource request
    Rest API ->> Rest API: Authorize student
    Rest API ->> Application: Create learning resource command
    Application ->> Persistence: Fetch entities
    Persistence ->> Application: Persisted entities:<br/>Learning Resource Definition<br/>Student profile
    Application ->> Domain: Adjust LRD with Random fact info
    Domain ->> Application: Adjusted LRD
    Application ->> Domain: Create personalized Learning Resource using<br/>LRD for the provided student
    critical Create Learning Resource Schema
        Domain ->> Wikimap: Get knowledge correlations
        Wikimap ->> Domain: Knowledge correlations
        Domain ->> Persistence: Get Learning History for<br/>elemental requirement qualification
        Persistence ->> Domain: Chosen Learning Results
        Domain ->> Domain: Calculate qualified requirements
        Domain ->> Domain: Create Personalized Theory & Activity<br/>details
        Domain ->> Persistence: Get Learning History for personalization rules
        Persistence ->> Domain: Chosen Learning Results
        Domain ->> Domain: Compute personalization rules for every personalized details
    end
    Domain ->> LLM: Learning Resource Generation Schema
    LLM ->> Domain: Learning Resource
    Domain ->> Application: Learning Resource Wrapper Result

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
