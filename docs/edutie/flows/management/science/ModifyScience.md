# Modify science flow

This flow modifies a lesson. This may be performed by any educator which is a lesson author.

## Sequence diagram

```mermaid
---
title: Science modification
---
sequenceDiagram
    participant Client
    participant Rest API
    participant Application
    participant Domain
    participant Persistence
    autonumber
    Client ->> Rest API: Modify Science Request
    Rest API ->> Rest API: Authorize Administrator
    Rest API ->> Application: Modify Science Command
    Application ->> Persistence: Load Data
    Persistence ->> Application: Loaded entities
    opt For every command property
        Application ->> Domain: Assign value to Science
        Domain ->> Application: Result of assignment
    end
    Application ->> Persistence: Save Science
    Persistence ->> Application: Result
    Application ->> Rest API: Result
    Rest API ->> Client: Result Response
```

## Input data

| Input               | Type        | Required |
|---------------------|-------------|----------|
| Educator User Id    | Educator Id | ✅        |
| Science Id          | Course Id   | ✅        |
| Science Name        | String      | ❌        |
| Science Description | String      | ❌        |
| Accessibility       | Boolean     | ❌        |

## Description

Flow updates course properties using the provided command.

- Science being modified is the Science with id provided in the command
- Any administrator can modify any science.
- Properties are assigned if they are provided.
    - Property is not assigned if it is assigned null explicitly or not provided in the command (assigned null
      implicitly)