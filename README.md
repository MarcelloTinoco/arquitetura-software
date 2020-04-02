# Montando a maquina para a arquitetura do siplad_v3
# Virtual Box - Versão 5.2.38 r136252 (Qt5.6.2)
# Ubuntu 16.04 - ubuntu-16.04.5-desktop-amd64.iso 

## Instalar o ubuntu 16.04.5

### Instalar as ferramentas de rede

- `sudo apt install net-tools`
- `ifconfig`

### Instalar o git. Foi utilizada a documentação 

https://git-scm.com/download/linux

- `sudo add-apt-repository ppa:git-core/ppa`
- `sudo apt update`
- `sudo apt install git`
- `git --version`


### Instalar o docker. Foi utilizada a documentacao

https://phoenixnap.com/kb/how-to-install-docker-on-ubuntu-18-04

-  `sudo apt-get update`
-  `sudo apt-get install apt-transport-https ca-certificates curl software-properties-common`
-  `sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add –`
-  `sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu  $(lsb_release -cs)  stable"`
-  `sudo apt-get update`
-  `sudo apt-get install docker-ce`
-  `systemctl status docker`

### Instalar o docker compose

-  `sudo curl -L "https://github.com/docker/compose/releases/download/1.23.1/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose`
-  `sudo chmod +x /usr/local/bin/docker-compose`


### Para colocar o NGinx Balanceando a carga na frente dos gateway
### Instalando o NGinx 
https://www.digitalocean.com/community/tutorials/como-instalar-o-nginx-no-ubuntu-18-04-pt

### Instalando o Java
-  `sudo apt-get install openjdk-8-jdk`
-  `java -version`

### Instalando o Maven

- `sudo apt install maven`
- `mvn -version`

## Links Uteis

### Spring Boot With Docker

https://spring.io/guides/gs/spring-boot-docker/

## Github Chris Richardson
https://github.com/cer/microservices-examples
https://github.com/cer/microservices-examples/blob/master/docker-compose-images.yml

## Docker ARGS, ENV

https://vsupalov.com/docker-arg-env-variable-guide/


## Executando o ecossistema no docker.

- `1 - Primeiro todos os artefatos devem ser construidos com o mvn clean package install -DskipTests`

- `2 - Depois executar o docker build, de cada artefato para criar as imagens`

- `3 - Ao final, depois que todas as imagens foram geradas, rodar o docker-compose que se encontra na raiz`

## Docker stack

`docker stack deploy -c docker-compose.yml siplad_v3`