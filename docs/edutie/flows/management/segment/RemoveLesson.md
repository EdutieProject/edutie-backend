# Remove lesson flow

This flow removes a segment. This may be performed by author educator of the segment.

## Sequence diagram

```mermaid
---
title: Segment removal
---
sequenceDiagram
    participant Client
    participant Rest API
    participant Application
    participant Domain
    participant Persistence
    autonumber
    Client ->> Rest API: Remove Segment Request
    Rest API ->> Rest API: Authorize Educator
    Rest API ->> Application: Remove Segment Command
    Application ->> Persistence: Load Data
    Persistence ->> Application: Loaded entities
    Application ->> Domain: Check if educator is author
    Domain ->> Application: true/false
    Application ->> Persistence: Remove Segment
    Persistence ->> Application: Result
    Application ->> Rest API: Result
    Rest API ->> Client: Result Response
```

## Input data

| Input            | Type            | Required |
|------------------|-----------------|----------|
| Educator User Id | UUID Identifier | ✅        |
| Segment Id       | UUID Identifier | ✅        |

## Description

Flow removes the segment from the application.

- Segment itself is being removed.
- Next segments (if any) are automatically being connected to the removed segment's previous element
- Segment may be removed only by author educator