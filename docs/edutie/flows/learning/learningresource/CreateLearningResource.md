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
    Application ->> Wikimap: Get knowledge correlations
    Wikimap ->> Application: Knowledge correlations
    Application ->> Domain: Create Personalized Activity Details
    Domain ->> Domain: Calculate Activity Personalization Rules
    Application ->> Domain: Activity Personalized Activity Details
    Application ->> Domain: Create Personalized Theory Details
    Domain ->> Domain: Calculate Personalization Rules
    Application ->> Domain: Personalized Theory Details
    Application ->> Domain: Create Learning Resource Generation Schema<br/>using personalized theory and activity details
    note left of Domain: Calculate qualified elemental requirements
    loop For every learning requirement in LRD
        Domain ->> Persistence: Get Student's Learning Results of L.Req. Id
        Persistence ->> Domain: Learning  Results
        Domain ->> Domain: Choose qualified elemental requirements
    end
    Domain ->> Application: Learning Resource Generation Schema
    note left of Application: Now, let LLM generate LR from LRGS
    Application ->> LLM: Learning Resource Generation Schema
    LLM ->> Application: Learning Resource
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

This flow creates a learning resource. It is created using learning resource definition and a student profile.

Learning resource is created by constructing Learning Resource Generation Schema.
Let's describe the algorithm behind the LRGS creation:

1. Input data: Student id and Learning Resource Definition id
2. Load the data - student profile and LR Definition
3. Create a Learning Resource Generation schema:
   1. For each learning requirement calculate the amount of qualified elemental requirements and add them to the LRGS
   2. Create Personalized Activity Details
      - Use activity prompts provided in the Learning Resource Definition
      - Create Activity Personalization Rules using Knowledge map
   3. Create Personalized Theory Details
       - Use theory prompts provided in the Learning Resource Definition
       - Create Theory Personalization Rules using Knowledge map

Personalized theory & activity details may be offloaded as they can be created separately

The LRGS is sent to the LLM and the response is being restructured to match Learning Resource.