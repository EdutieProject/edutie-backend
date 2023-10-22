# Domain layer
The most important layer - it is the core of our application. One who
does not know anything about the project should be enlightened after looking even
at the naming of every class which lives here. 

We can distinguish 2 domain models: anemic and rich

**We will be using an anemic domain model.**

# Anemic domain model
Anemic domain model is a domain consisting of plain classes with only fields. In
this model domain entities contain no logic and all the logic is handled by application
layer.
Focus should lie on keeping this layer as independent as possible from external dependencies.
Thus, the only dependencies used here should be lombok and/or JPA.