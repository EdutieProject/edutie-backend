# Architecture

Edutie application is made in the monolith-with-satellites style, where certain tasks are delegated to the stateless
microservices that are designed to perform a task and provide a value to for the Edutie Backend app. 

These are the systems with their responsibilities described:

## Api Gateway

Edutie api gateway serves as a gateway for incoming requests. It is the *barrier* that covers the overall system from
the outside word, therefore only using the gateway can one access the backend.

## BFF

Backend-for-frontend serves as an authentication utility system. It is serves as a middleware for handling the
authentication for client's frontend application. In current case, it handles session management for the client
and handles session-to-jwt exchange. More in auth docs in the neighbouring folder.

## Authorization Server

Authorization server is the system that is responsible for handling user identity. It is implemented as keycloak OpenID
provider. It handles user auth together with BFF system.

## Edutie Backend Application

The main system in which these docs are placed. Edutie Backend is the main system responsible for
all domain related logic. All the logic that is irreplaceable lives in here. If the part of the application is
susceptible to immense conceptual changes, the general idea states that we abstract those parts away to a microservice.

## Knowledge Map Service

The service responsible for knowledge correlation identification. This system was extracted because of uncertainty
regarding
the used technology. Currently, it is based on wikimedia API and graph-like links structure between wikipedia articles.

## LLM Service

LLM service is extracted to a microservice as most of LLMs serve python packages for their models. Additionally,
we can send *generation schemas* to the LLM which encompass all the necessary data to generate content. Therefore,
LLM service will be additionally responsible for structuring this data into a prompt.
