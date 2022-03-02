# User Registration Kata
This project contains a solution for a user registration Kata that handle user registration and user details management.

## Objective
The main objective of this project is to have high quality code and tests, in compliance with best practices.

## Approach and Architecture
This project follow [Test-driven development (TDD)](https://en.wikipedia.org/wiki/Test-driven_development) software development process.

## Installing / Getting started
This is a Spring Boot and Maven project, so it can directly run via maven with the following command:
- Via Maven:
```shell
mvn spring-boot:run
```
- Via Maven Wrapper:
```shell
./mvnw spring-boot:run
```

The project contains two REST APIs, the first one handle user registration and the second one returns details of a specific user.
Find below a specification and how to use each of them :

- User Registration API:

URL : ```http://localhost:8080/user-management/users ```

HTTP method: _POST_

Request body schema:
```json
{
  "username" : "string",
  "birthday" : "xxxx-xx-xx",
  "country" : "string",
  "phone" : "string",
  "gender" : "enum"
}
```

> _username_, _birthday_ and _country_ are mandatory and unique.

> _country_ value should be France.

> _birthday_ value should be grater then 18 years.

> _gender_ possible values are MALE, FEMALE or OTHERS.

Success response HTTP status: _201_ (Created)

Success response body schema:
```json
{
  "id" : 1,
  "username" : "string",
  "birthday" : "xxxx-xx-xx",
  "country" : "string",
  "phone" : "string",
  "gender" : "enum"
}
```

- User Details API:

URL : ```http://localhost:8080/user-management/users/{id} ```

HTTP method: _GET_

Path variable: _id_ (e.g: 1)

Success response HTTP status: _200_ (Ok)

Success response body schema:
```json
{
  "id" : 1,
  "username" : "string",
  "birthday" : "xxxx-xx-xx",
  "country" : "string",
  "phone" : "string",
  "gender" : "enum"
}
```

## Developing
### Built With
Technologies used in this project are:
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/)
- [JUnit 5](https://junit.org/junit5/)

### Prerequisites
- Java 11 or higher version already installed

## Tests
This project contains Integration and Unit tests that can be run via maven with the following command:
- Via Maven:
```shell
mvn test
```
- Via Maven Wrapper:
```shell
./mvnw test
```

## Other documentations support
Please find technical documentation (JavaDoc) and request samples (Postman collection) in [**_docs_**](/docs) folder.

