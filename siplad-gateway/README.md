# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/maven-plugin/)

### Guides
The following guides illustrate how to use some features concretely:

* [Routing and Filtering](https://spring.io/guides/gs/routing-and-filtering/)

# Spring Cloud Netflix Maintenance Mode

The dependencies listed below are in maintenance mode. We do not recommend adding them to
new projects:

*  Zuul

The decision to move most of the Spring Cloud Netflix projects to maintenance mode was
a response to Netflix not continuing maintenance of many of the libraries that we provided
support for.

Please see [this blog entry](https://spring.io/blog/2018/12/12/spring-cloud-greenwich-rc1-available-now#spring-cloud-netflix-projects-entering-maintenance-mode)
for more information on maintenance mode and a list of suggested replacements for those
libraries.

# Autenticação centralizada

`https://spring.io/guides/tutorials/rest/`

`https://www.baeldung.com/spring-security-zuul-oauth-jwt`

`https://medium.com/@arjunac009/spring-boot-microservice-with-centralized-authentication-zuul-eureka-jwt-5719e05fde29`

`https://medium.com/@mool.smreeti/microservices-with-spring-boot-authentication-with-jwt-and-spring-security-6e10155d9db0`



# Montar e executar

- `mvn clean package install -DskipTests`

- `java -jar -Dserver.port=8900 target/siplad-gateway-1.0.jar`

# Criar a imagem

- `docker build -t siplad/service-gateway:1.0 .` 

# Docker compose

- `docker compose up`

