# The test task in CUBICFOX #

Your task is to implement a REST endpoint testing software in java 8+. Any other framework or technology can be used. The suggested database is PostgreSQL.

Your code has to call the following endpoint, and handle all the data you will get back in the response.
https://jsonplaceholder.typicode.com/users

- [x] Store the users and the data connected to them in a database.
- [x] Check if it was a successful http call and you get back the response correctly (same count of users every time and 200 OK http status).
- [x] Create a log file automatically which contains the details of the api calls and the response statuses. 
- [x] Validate all the user emails (text@text.tld).

## Technologies ##
- Java 8+, Stream API, Java Core.
- Flyway.
- Spring IoC, Boot.
- Maven.

## Database ##
- PostgreSQL.

## How to start BMW-API using Docker? ##
1. Install Java 8+.
2. Install Docker.
3. Copy this project by the link `https://github.com/gigbat/test-cubicfox.git`.
4. Go to the root folder and run `docker compose build`.
5. Go to the root folder and open the `docker-compose.yaml` file. Run all services. (Or you can just execute this command `docker compose up`.)
6. Go to your browser and type in the search section `http://localhost:8080/`

:exclamation: Please, after finished working with Docker stop all containers to avoid any conflicts :exclamation:
`docker compose down`.

## How to start BMW-API using the starter class? ##
1. Install Java 8+.
2. Install any IDEA. Recommended Intellij IDEA.
3. Install maven and configure it.
4. Install the PostgreSQL server.
5. Copy this project by the link `https://github.com/gigbat/test-cubicfox.git`.
6. Go to the resources folder and replace the line `postgres.url=jdbc:postgresql://postgres:5432/` exactly word `postgres` to `localhost`.
7. Go to the PostgreSQL server and create the server with the name `postgres`, create the user with the name `postgres`, the password - `password`, the database - `cubicfox`.
8. Go to the project and start the starter class named `TestApplication.java`.
9. Go to your browser and type in the search section `http://localhost:8080/`

## How to run UNIT tests ##
1. Install Java 8+.
2. Install any IDEA. Recommended Intellij IDEA.
3. Install maven and configure it.
4. Install the PostgreSQL server.
5. Copy this project by the link `https://github.com/gigbat/test-cubicfox.git`.
6. Go to the resources folder and replace the line `postgres.url=jdbc:postgresql://postgres:5432/` exactly word `postgres` to `localhost`.
7. Go to the PostgreSQL server and create the server with the name `postgres`, create the user with the name `postgres`, the password - `password`, the database - `cubicfox`.
8. Go to the test folder and run all scenarios.

## How work with BMW-API ##

- Once you start the server and type in the search section `http://localhost:8080` you save immediately data in our database. Our endpoint is configured on the main page, but it can be replaced on another path just by replacing the annotation `@GetMapping` on yourself.
- If you try to do the GET request one more time you will get an exception. You need to drop your tables an try to send a request again.
- Be careful:exclamation: If you run the server using the starter class or unit tests you need to replace in the property file the line `postgres.url=jdbc:postgresql://postgres:5432/` exactly word `postgres` to `localhost`.
- Be careful:exclamation: If you run the server using Docker you need to replace in the property file the line `postgres.url=jdbc:postgresql://localhost:5432/` exactly word `localhost` to `postgres`.
