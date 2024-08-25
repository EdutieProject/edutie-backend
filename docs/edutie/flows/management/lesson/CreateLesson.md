# Create lesson flow

This flow creates a lesson. This may be performed by any educator.

## Sequence diagram

```mermaid
---
title: Lesson creation
---
sequenceDiagram
    participant Client
    participant Rest API
    participant Application
    participant Domain
    participant Persistence
    autonumber
    Client ->> Rest API: Create Lesson Request
    Rest API ->> Rest API: Authorize Educator
    Rest API ->> Application: Create Lesson Command
    Application ->> Persistence: Load Data
    Persistence ->> Application: Loaded entities
    Application ->> Domain: Create Lesson as next
    Domain ->> Domain: Initialize lesson with root segment
    Domain ->> Application: Wrapper Result
    Application ->> Persistence: Deep save lesson
    Persistence ->> Application: Result
    Application ->> Rest API: Wrapper Result
    Rest API ->> Client: Response with created lesson
```

## Input data

| Input              | Type        | Required |
|--------------------|-------------|----------|
| Educator User Id   | Educator Id | ✅        |
| Lesson Name        | String      | ✅        |
| Lesson Description | String      | ❌        |
| Previous Lesson Id | Lesson Id   | ✅        |
| Next Lesson Id     | Lesson Id   | ❌        |

## Description

Flow creates a course using provided command.

- Lesson uses name and description provided in the command
- Lesson is created as next in relation to the previous lesson identified by the id in the command.
- Lesson is created in between 2 lessons if there is next lesson Id provided.
- If any of the provided information is incorrect, flow does not create anything.