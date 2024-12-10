# Get Latest Activity

This ancillary flow is responsible for providing latest activity indicators for the student invoking the flow.

## Sequence diagram

```mermaid
---
title: Get Random Fact Qeury
---
sequenceDiagram
    participant Client
    participant Rest API
    participant Application
    participant Domain
    participant Persistence
    autonumber
    Client ->> Rest API: Get Latest Activity request
    Rest API ->> Rest API: Authorize student
    Rest API ->> Application: Latest Activity Query
    Application ->> Domain: Get latest student's learning result
    Domain ->> Persistence: Get learning result of student
    Persistence ->> Domain: Learning Result
    Domain ->> Application: Learning Result
    opt Learning Result Definition Type is DYNAMIC
        Application ->> Application: Create Latest Activity View with<br/>Latest Result only
        Application ->> Rest API: Wrapper Result of Latest Activity View
        Rest API ->> Client: Latest Activity View Response
    end
    Application ->> Domain: Get source course of result
    Domain ->> Persistence: Get Latest Result's course
    Persistence ->> Domain: Course
    Domain ->> Application: Latest Course
    Application ->> Domain: Calculate Course Progress
    Domain ->> Persistence: Get Passed Results of given course
    Persistence ->> Domain: Learning Result List
    Domain ->> Application: Course Progress indicator
    Application ->> Application: Create Latest Activity View<br/>using latest result and course view
    Application ->> Rest API: Wrapper Result of Latest Activity View
    Rest API ->> Client: Latest Activity View Response
```

## Input data

| Input                    | Type            | Required |
|--------------------------|-----------------|----------|
| Student Id               | UUID Identifier | ✅        |

## Description

Flow retrieves the latest activity view encompassing latest activity descriptors such as:
 - Latest learning result
 - Latest Course View consisting of the course itself and a course progress indicator