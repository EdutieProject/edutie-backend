# Domain layer
The core of our application. Here live all domain entities and aggregates.

## Logic contained

All logic contained within this layer is domain-characteristic. Basically what
this means is that the said logic is dependent only on different domain entities.

## File structure

Files are structured in a way that is easily conceivable.
 - domain
    - common - this is the folder which contains concepts shared across the whole domain
    - *learningresource, student, etc...* - folders containing aggregates

## Aggregates
Aggregate is basically an entity, which other entities are dependent on. All the
concepts within aggregates should be unique to them, if it is not unique let's place
them into the common folder.