# Create segment flow

This flow creates a segment. This may be performed by any educator that has the permissions to edit contents of the
corresponding lesson.

## Sequence diagram

```mermaid
---
title: Segment creation
---
sequenceDiagram
    participant Client
    participant Rest API
    participant Application
    participant Domain
    participant Persistence
    autonumber
    Client ->> Rest API: Create Segment Request
    Rest API ->> Rest API: Authorize Educator
    Rest API ->> Application: Create Segment Command
    Application ->> Persistence: Load Data
    Persistence ->> Application: Loaded entities
    Application ->> Domain: Create Segment as next
    Domain ->> Application: Segment
    Application ->> Persistence: Save segment
    Persistence ->> Application: Result
    opt Next segment Id provided
        Application ->> Domain: Change previous element<br/>of the "next" segment to the created one
        Domain ->> Application: Result
        Application ->> Persistence: Save next segment
        Persistence ->> Application: Result
    end
    Application ->> Rest API: Wrapper Result
    Rest API ->> Client: Response with created segment
```

## Input data

| Input               | Type        | Required |
|---------------------|-------------|----------|
| Educator User Id    | Educator Id | ✅        |
| Segment Name        | String      | ✅        |
| Segment Description | String      | ❌        |
| Previous Segment Id | Lesson Id   | ✅        |
| Next Segment Id     | Lesson Id   | ❌        |

## Description

Flow creates a course using provided command.

- Segment uses name and description provided in the command
- Segment is created as next in relation to the previous segment identified by the id in the command.
- Segment is created in between 2 segments if there is next segment Id provided.
- If any of the provided information is incorrect, flow does not create anything.