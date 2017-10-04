Spring Boot, Angular, Tomcat Embedded, Swagger, JPA + Hibernate and H2 Embedded
---------------------------------------------------

Tools used:
-----------

* Maven 3.5
* Spring Boot 1.5.7-RELEASE
* Hibernate embedded
* H2 1.4.196
* Angular 1.6.4
* Bootstrap 3.3.7
* JUnit 4.12
* Swagger 2.7.0

BitBucket
---------

Clone the repository:

    $ git clone https://bitbucket.org/thiago_marsal/spring-boot-jpa-embedded.git

Run Project
-----------

**Run Application**

    $ mvn spring-boot:run
    http://localhost:8080/

You can see the API Docs and Test using:

Swagger:
* [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Angular:
* [http://localhost:8080/v2/api-docs](http://localhost:8080/v2/api-docs)


**Run Tests**

You can run only Junit tests using:

    $ mvn test:test
