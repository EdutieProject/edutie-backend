# Create science flow

This flow creates a science. This flow may be invoked by an administrator user.

## Sequence diagram

```mermaid
---
title: Science creation
---
sequenceDiagram
    participant Client
    participant Rest API
    participant Application
    participant Domain
    participant Persistence
    autonumber
    Client ->> Rest API: Create Science Request
    Rest API ->> Rest API: Authorize Administrator
    Rest API ->> Application: Create Science Command
    Application ->> Domain: Create Science
    Domain ->> Application: Science
    Application ->> Persistence: Save science
    Persistence ->> Application: Result
    Application ->> Rest API: Wrapper Result
    Rest API ->> Client: Response with created science
```

## Input data

| Input                 | Type            | Required |
|-----------------------|-----------------|----------|
| Administrator User Id | UUID Identifier | ✅        |
| Science Name          | String          | ✅        |
| Science Description   | String          | ❌        |

## Description

Flow creates a science using provided command.

- Science is inaccessible by default. It may be made accessible using modification flow.