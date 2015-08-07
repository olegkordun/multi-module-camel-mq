Multi module project with Apache Camel and Active MQ
===============================================

This is a quick demo of multi module maven project using following open source components:
 * Spring Framework, MVC, Security
 * Spring Data
 * FlywayDb
 * Dbunit
 * Rest Assured
 * Spring MVC tests
 * Apache Camel
 * Apache MQ

The main feature is to keep front module with REST API separated from the backend by Active MQ message broker.
Apache Camel provides "Producer" template to connect components via MQ with RPC style calls. It is quick and easy to start and
we can write some more complicated load-balancing solution later with Camel.

![App structure](./images/struct.png)

# Modules
* common-config - common Spring Framework dependencies
* common-json - json beans
* common-persistent - entities and Spring Data repositories.
Uses Flywaydb to organise and deploy database migrations (useful for some continuous integration).
* enums - just common enums used in the back and front end
* common-messaging - common DTO, RPC interfaces and camel routes
* office-frontend - REST api to create\update users and companies. Uses Spring Security to protect API.
* office-backend - services
* integration-test - see next section.

![Modules structure](./images/modules.png)

Functional segregation of modules simplifies creation of new applications. E.g. new application for the backend can use existing persistent
module. Also MQ connection between modules could be provided with just one messaging block. Etc.

# Unit tests
Few unit tests written in office-frontend module. Mockito and spring mvc test frameworks are used.

# Integration tests
It's hard to test multi component project with just unit tests. I found much easier way - integration tests with all components involved.

This sub-project starts embedded Jetty, HSQLDB, ActiveMQ on "mvn verify".

Backend is deployed to Jetty with HSQLDB instance at the beginning.
Then Flywaydb apply sql migrations and creates database structure.
Each integration test cleans up the DB and inserts data from test data sets.
Tests both backend and front REST services using Rest Assured and DbUnit.
After REST-services call database is compared with expected data set.
No need of any external database, MQ broker or any manual configuration.

