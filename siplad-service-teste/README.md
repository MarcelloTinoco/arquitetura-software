# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/maven-plugin/)

### Sobre

Este projeto foi implementado para testar a comunicao entre micro servicos que estao com seguranca JWT aplicada.

Ao invocar o endpoint http://localhost:9600/v1/testes/1 o servico ira validar o token e fara uma chamada para o servico siplad-service-apoio endpoint http://localhost:9300/v1/apoio/acoes/{id}.
A invocacao e feita pelo Feign Client que encapsula a chamada ao endpoint bem como a descoberta onde o servico apoio se encontra. Ele sabe onde o servico esta atraves do service discovery. 
