# File paging backend

Submission file paging system backend. System help to obtain and display paged data for submission files including attributes. System basically generates queries for paging requested information. Query Generation is not trivial as attributes are represented by a one to many relation and not stored as columns. 


## Data Model Project
Contains JPA entities and querydsl classes that helps to generate strongly types query operations.

## Web Api Project
Spring-boot application contained rest services to support submission file paging.


# Getting started
Import project as an standard gradle project.

## Useful commands.
| Command                     | Description                         | Path           |
|-----------------------------|-------------------------------------|----------------|
| ./gradlew build             | build project and execute unit test | root path      |
| ./gradlew :web-api:bootRun  | run application                     | web-api folder |



