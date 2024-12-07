# Edutie backend application

Welcome to the edutie backend application.

## Developer's documentation

Documentation for developers is available in `/docs` folder.

The structure of the dev-docs:

### `/architecture`

This folder contains documentation regarding the overall project architecture, describing different
repositories and sub-systems as well as the communication with them.

### `/auth`

This folder contains documents about the authentication and authorization used in the application.

### `/edutie`

This folder contains documentation of the Edutie domain and application. This part defines the entities as well as the 
business flows in the application.

Business flows are implemented as concrete use case handlers and defined entities are implemented as aggregates.

## OpenApi documentation

To browse open api SWAGGER endpoint documentation visit http://localhost:8081/docs url.

The JSON OpenApi specification is present under http://localhost:8081/docs/spec url.

## Run inside docker

### Default docker-compose

Running the app using *docker-compose.yaml* is the default. It runs the edutie-backend with the database only.

1. `docker-compose build` - this ensures the codebase is refreshed and up-to-date
2. `docker-compose up`

To run those commands you must be in the exact same folder as the `docker-compose.yaml` file.

### Collective compose

Running the app using *compose-collective.yaml* is the new way of running Edutie. This way utilizes the convention in
which all the sub-system repositories are a sibling folders to the edutie-backend folder. Example:
 - *Top-level-folder*
   - edutie-backend
   - edutie-llm
   - edutie-wikimap
   - *etc.*

If repositories are structured this way, we can use collective compose to run all the backend systems at once:

```shell
docker-compose -f compose-dev.yaml up
```

#### ⚠ Caution!
To update the images it is still better to use the default *docker-compose.yaml* as it builds the same image.

To do so type:

```shell
docker-compose build
```

This will update the image. The collective compose will use the image built by the default docker-compose.

This approach is recommended and is supported by the rest of repositories. The singular (default) docker-composes should
be used to build an image, which will be later used for collective compose. It is advised to use collective compose unless
testing a specific app in the separated context.