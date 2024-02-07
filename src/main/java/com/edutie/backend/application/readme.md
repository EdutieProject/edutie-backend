# Application layer a.k.a. "Use cases" layer

Application layer is the place where all the core logic should be placed. In some of
the solutions there is no distinction between domain layer and use cases layer and
those two packages are merged together. Distinct use case layer usually goes with
anemic domain model, where domain layer consists mostly of Plain Old Java Objects 
(POJOs) with no methods.