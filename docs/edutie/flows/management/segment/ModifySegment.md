# Modify lesson flow

This flow modifies a segment. This may be performed by any educator which is a segment author.

## Sequence diagram

```mermaid
---
title: Segment modification
---
sequenceDiagram
    participant Client
    participant Rest API
    participant Application
    participant Domain
    participant Persistence
    autonumber
    Client ->> Rest API: Modify Segment Request
    Rest API ->> Rest API: Authorize Educator
    Rest API ->> Application: Modify Segment Command
    Application ->> Persistence: Load Data
    Persistence ->> Application: Loaded entities
    Application ->> Domain: Check if educator is author
    Domain ->> Application: true/false
    opt For every command property
        Application ->> Domain: Assign value to Segment
        Domain ->> Application: Result of assignment
    end
    Application ->> Persistence: Save Segment
    Persistence ->> Application: Result
    Application ->> Rest API: Result
    Rest API ->> Client: Result Response
```

## Input data

| Input                       | Type        | Required |
|-----------------------------|-------------|----------|
| Educator User Id            | Educator Id | ✅        |
| Segment Id                  | Course Id   | ✅        |
| Segment Name                | String      | ❌        |
| Segment Snippet Description | String      | ❌        |

## Description

Flow updates course properties using the provided command.

- Segment being modified is the segment with id provided in the command
- Educator modifying the segment must be an author of the segment to modify it.
- Properties are assigned if they are provided.
    - Property is not assigned if it is assigned null explicitly or not provided in the command (assigned null
      implicitly)