# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/maven-plugin/)

### Guides
The following guides illustrate how to use some features concretely:

* [Service Registration and Discovery](https://spring.io/guides/gs/service-registration-and-discovery/)

# Montar e executar

- mvn clean package install -DskipTests

- java -jar -Dserver.port=8900 target/siplad-service-discovery-1.0.jar

# Criar a imagem

- docker build -t siplad/service-discovery:1.0 .