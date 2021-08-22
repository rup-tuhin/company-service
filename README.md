# Project Description
A REST API in Java (spring boot). Used In-Memory H2 database. 
There is a Spring Swagger UI to get an overview of the available APIs. 

The API has two Domain Object: Company and Owner.

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

There is a backend that can check the Social Security Number by randomly returning "valid/invalid".

The API has two users defined.
1. user/Password : Has access to all "/company/\*" resources. Cannot view Social Security Number of Owners.
2. admin/P@$$w0rd! : Has access to all "/company/\*" resources. Can view the Social Security Number of Owners. 

# Getting Started

Check out the code and perform a maven build

      $ mvn clean package

Run the app using maven plugin
      
      $ mvn spring-boot:run

Once started, You can import the APIs in Postman using the below link :

   - http://localhost:8080/v2/api-docs

Check the H2 DB in the below URL. Login using credential - sa/password.

   - http://localhost:8080/h2-console/

The Application is also runnable on Docker platform. 

Perform the below step to get the image registered in local Docker platform

1. docker build -t spring/company-service:1.0 .
2. docker run -p 8080:8080 spring/company-service:1.0
   
    * Check the swagger ui - http://localhost:8080/swagger-ui/index.html

3. To perform the below operations use CURL command << as Admin user >>
   
   a. Create a New company :
    
    - curl -X POST "http://localhost:8080/company" -H "accept: \*/\*" 
      -H "Content-Type: application/json"
      -H "Authorization: Basic YWRtaW46UEAkJHcwcmQh"
      -d "{ \"companyName\": \"Test Company\", \"country\": \"India\", \"owners\": [ { \"name\": \"Bijay Singh\", \"socialSecurityNumber\": \"489\" }, { \"name\": \"Ajay Singh\", \"socialSecurityNumber\": \"123\" } ], \"phoneNumber\": \"789456\"}"
    
    b. Get a list of all companies :

    - curl -X GET "http://localhost:8080/company" -H "accept: \*/\*" -H "Authorization: Basic YWRtaW46UEAkJHcwcmQh"
    
    c. Get details about a company :
    - curl -X GET "http://localhost:8080/company/1 " -H "accept: \*/\*" -H "Authorization: Basic YWRtaW46UEAkJHcwcmQh"
    
    d. Update a company
   
    -  curl -X PUT "http://localhost:8080/company/1" 
       -H "accept: \*/\*" -H "Content-Type: application/json"
       -H "Authorization: Basic YWRtaW46UEAkJHcwcmQh"
       -d "{ \"companyName\": \"Updated Company\", \"country\": \"USA\", \"owners\": [ { \"name\": \"Bijay Singh\", \"socialSecurityNumber\": \"489\" }, { \"name\": \"Ajay Singh\", \"socialSecurityNumber\": \"123\" } ], \"phoneNumber\": \"456789\"}"
 
    e. Add an owner of the company
    - curl -X POST "http://localhost:8080/company/1/owner" -H "accept: \*/\*"
      -H "Content-Type: application/json"
      -H "Authorization: Basic YWRtaW46UEAkJHcwcmQh"
      -d "{ \"name\": \"Dillip Singh\", \"socialSecurityNumber\": \"741\"}"
    
    f. Check of Social Security Number
    - curl -X GET "http://localhost:8080/company/{id}/owner/validate?ssNumber=342" 
      -H "accept: \*/\*" 
      -H "Authorization: Basic YWRtaW46UEAkJHcwcmQh"
      
4. Accessing the URL as user account. This wont show the SSN of Owners
    
    a. Get a list of all companies :

    - curl -X GET "http://localhost:8080/company" -H "accept: \*/\*" -H "Authorization: Basic dXNlcjpQYXNzd29yZA=="

    b. Get details about a company :
    
    - curl -X GET "http://localhost:8080/company/1 " -H "accept: \*/\*" -H "Authorization: Basic dXNlcjpQYXNzd29yZA=="

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.4/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.5.4/reference/htmlsingle/#using-boot-devtools)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.5.4/reference/htmlsingle/#production-ready)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.5.4/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.5.4/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Swagger UI](http://springfox.github.io/springfox/docs/current/)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

