# Create learning resource flow

This flow creates personalized, dedicated learning resource for a student using a Learning Resource Definition.

## Sequence diagram

```mermaid
---
title: Learning Resource creation
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

| Input                           | Type            | Required |
|---------------------------------|-----------------|----------|
| Student Id                      | UUID Identifier | ✅        |
| Learning Resource Definition Id | UUID Identifier | ✅        |

## Description

This flow creates a learning resource. It is created using learning resource definition as an absolute definition
and a student's learning history for personalization purposes.

1. Input data: Student id and Learning Resource Definition id
2. Load the data - student profile and LR Definition
3. Create a Personalized Learning Resource:
    1. Fetch knowledge correlations from knowledge map
    2. Create Learning Resource Generation Schema
        1. Compute qualified learning requirements using recent learning history
        2. Create Personalized Activity Details
            - Use activity prompts provided in the Learning Resource Definition
            - Create Activity Personalization Rules using Knowledge map
        3. Create Personalized Theory Details
            - Use theory prompts provided in the Learning Resource Definition
            - Create Theory Personalization Rules using Knowledge map
    3. Use LLM to generate LR dto from LRGS
    4. Create Learning Resource from the DTO
4. Save & return the LR.

Personalized theory & activity details may be offloaded as they can be created separately

The LRGS is sent to the LLM and the response is being restructured to match Learning Resource.