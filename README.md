# Project Description
Create a tiny REST API in Java (spring boot) or C# (.net core) that is runnable with Docker.

The API should have two concepts: company and owner.

A company has the following attributes:

    - Company Name
    - Country
    - Phone Number
    - One or more owners

An owner has the following attributes:
    - Name
    - Social Security Number

The API may support:
    -   Create new company
    -   Get a list of all companies
    -   Get details about a company
    -   Update a company
    -   Add an owner of the company
    -   Check of Social Security Number

Simulate a backend that can check the Social Security Number by randomly returning "valid/invalid".

The API should define at least two access groups; e.g. one that will not be able to read the Social Security Number.

# Getting Started
-Docker build

1. docker build -t spring/company-service:1.0 .
2. docker run -p 8080:8080 spring/company-service:1.0
3. verify - curl -X GET "http://localhost:8080/company"

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.4/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.5.4/reference/htmlsingle/#using-boot-devtools)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.5.4/reference/htmlsingle/#production-ready)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.5.4/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.5.4/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

