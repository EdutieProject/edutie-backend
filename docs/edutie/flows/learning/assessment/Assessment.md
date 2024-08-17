# Assessment flow
This flow is responsible for student's work assessment. It creates Solution Submission as well as corresponding 
Learning Result.

## Sequence diagram

```mermaid
---
title: Assessment Flow
---
sequenceDiagram
    participant Client
    participant Rest API
    participant Application
    participant Domain
    participant Persistence
    participant LLM
    autonumber
    
    Client->>Rest API: Assessment request
    Rest API->>Rest API: Authorize student
    Rest API->>Application: Assessment command
    Application->>Persistence: Fetch entities
    Persistence->>Application: Persisted entities:<br/>Learning Resource<br/>Student profile
    Application->>Domain: Create Solution Submission
    Domain->>Application: Solution Submission
    Application->>Persistence: Save Solution Submission
    Persistence->>Application: Result
    Application->>Domain: Create Learning Result Creation Schema
    Domain->>Application: Learning Result Creation Schema
    Application->>LLM: Learning Result Creation Schema
    LLM->>Application: Learning Result DTO
    Application->>Domain: Learning Result DTO
    Domain->>Application: Learning Result
    Application->>Persistence: Save Learning Result
    Persistence->>Application: Save Result
    Application->>Rest API: Result wrapping Learning Result
    Rest API->>Client: Learning Resource Response

```

## Input data

| Input                    | Type                 | Required |
|--------------------------|----------------------|----------|
| Student Id               | Student Id           | ✅        |
| Learning Resource Id     | Learning Resource Id | ✅        |
| Solution Submission Text | String               | ✅        |
| Hints Revealed Count     | Integer              | ✅        |

## Description
This flow assesses student's work. Let's break it down into steps:
1. We need to create a Solution Submission entity from the input data
2. Save Solution Submission.
3. Fetch Learning Resource from persistence
4. Merge those into DTO
5. Send the DTO to LLM service
6. Let LLM assess the work:
   1. LLM assesses the work according to each problem generated. The information for each problem is in stored in the LR.
   2. Qualified sub requirements must be accounted for when assessing regarding each learning requirements 
     - Therefore, assessments must also include the info how many sub reqs are assessed
     - Furthermore, assessment's grade represents the assessment of work that was made for the qualified sub reqs.
7. Group the assessments and pack them into Learning Result. 