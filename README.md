# Mini Item Directory — Backend

Spring Boot REST API for the Mini Item Directory: a small app where a user can
add an item (name + category) and search items by name. This repository holds
the **backend**; the Angular frontend is in a separate repository.

## Live demo

- **API (this service):** https://miniitemdirectorybackend.onrender.com

Frontend repo: https://github.com/Kk-1020/MiniItemDirectoryFrontEnd

> Note: the API runs on a free hosting tier, so the first request after a period
> of inactivity may take ~30–60 seconds while the service wakes up.

## Tech stack

- Java 21, Spring Boot 3.3 (Spring Web, Spring Data JPA)
- H2 in-memory database
- JUnit 5 + Mockito for testing
- Maven build

## How to run locally

**Prerequisites:** Java 21 and Maven 3.8+.

```bash
mvn spring-boot:run
```

The API starts on http://localhost:8080.

## API endpoints

### POST /items — create an item

Request:

```
POST /items
Content-Type: application/json

{ "name": "Atomic Habits", "category": "Book" }
```

Response — `201 Created`:

```json
{ "id": 1, "name": "Atomic Habits", "category": "Book" }
```

### GET /items?q=... — list / search items

Returns all items, or only those whose name contains `q` (case-insensitive).
With no `q`, it returns the full list.

Request: `GET /items?q=atom`

Response — `200 OK`:

```json
[ { "id": 1, "name": "Atomic Habits", "category": "Book" } ]
```

### Validation

`name` is required and limited to **100 characters**; `category` is required and
limited to 50 characters. An empty `name` returns `400 Bad Request`:

```json
{ "status": 400, "error": "Bad Request", "fieldErrors": { "name": "name must not be empty" } }
```

## How to test it

Run the unit test (JUnit 5 + Mockito):

```bash
mvn test
```

Two example cases (against the running API):

1. **Add and search:** `POST /items` with `{"name":"Atomic Habits","category":"Book"}`,
   then `GET /items?q=atom` — the item is returned.
2. **Validation:** `POST /items` with `{"name":"","category":"Book"}` — the API
   responds `400 Bad Request`.

## Known limitations

- Data is stored in an in-memory H2 database, so all items are lost on restart.
- No edit or delete — only create and search (per the exercise scope).
- No pagination, sorting, or filters beyond the single `q` parameter.
- No authentication.
- CORS is open to all origins to keep the demo simple; a real app would restrict it.

## Deployment note

The brief asked not to build Docker. Render does not run JVM apps natively, so
this repo includes a single minimal `Dockerfile` used purely as the deployment
mechanism for the live demo. There is no Docker Compose, orchestration, or
CI/CD — the app itself is a plain Spring Boot project.

## Approximate hours spent

~5 hours (build, environment setup, deployment, and documentation).

## Use of AI coding tools

I used Claude (Anthropic) to scaffold the project, generate boilerplate for the
controller/service/repository layers, and draft this README. I reviewed all
generated code, ran the build and tests, and handled deployment myself.
