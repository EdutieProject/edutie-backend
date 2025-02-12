# Edutie backend application

Welcome to the edutie backend application.

## Developer's documentation

Whole documentation is provided in `edutie-docs` repository. You may find it [here](https://github.com/EdutieProject/edutie-docs).

## OpenApi documentation

To browse open api SWAGGER endpoint documentation visit http://localhost:8081/docs url.

The JSON OpenApi specification is present under http://localhost:8081/docs/spec url.

## Run inside docker

### Default docker-compose: standalone

Running the app using *docker-compose.yaml* is the default. It runs the edutie-backend with the database only.

1. `docker-compose build` - this ensures the codebase is refreshed and up-to-date
2. `docker-compose up` - runs the app with the DB

### Development environment - collective compose

Running the app using *compose-dev.yaml* is the recommended way of running Edutie app. The compose sets up the development
environment.

This way utilizes the convention in which all the sub-system repositories are a sibling folders to the edutie-backend 
folder. Example:
 - *Top-level-folder*
   - edutie-backend
   - edutie-llm
   - edutie-knowledge-map
   - *etc.*

If repositories are structured this way, we can use collective dev compose to run the whole backend system at once:

```shell
docker-compose -f compose-dev.yaml up
```

### Production environment

Running the production environment is supported by *compose-prod.yaml*. It behaves in the same way as the dev compose,
but runs more applications. This setup is prepared for the deployment architecture. Note that the production env has 
different connection structure. Moreover, it uses different environment .env file.

Run the prod env using:

```shell
docker-compose -f compose-prod.yaml up
```

### Environment configuration

Environment configurations requires certain .env files to be set up. The sample .env files for given settings are provided,
however for production setup it requires replacing some values.

Note that the main .env file that is provided in this repo, provides most of but not all environment vars & secrets. 
There are also .env files in frontend & subsystem repos that need to be filled in.

### Building images
To build docker images use docker-composes associated with the environment you want to run, e.g.

```shell
docker-compose -f compose-prod.yaml build bff 
```

This will update the BFF app image.

Building the image with the default (standalone) composes should work too, as the image names are unified across 
environments.
