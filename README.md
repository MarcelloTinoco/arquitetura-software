Montando a maquina para a arquitetura do siplad_v3

- No Ubuntu 18.04

- Instalar o ubuntu 18.04

- Instalar as ferramentas de rede

# sudo apt install net-tools

-> para testar 

# ifconfig

- Instalar o git. Foi utilizada a documentação 

https://git-scm.com/download/linux

# sudo add-apt-repository ppa:git-core/ppa
# sudo apt update
# sudo apt install git

-> para testar
#git --version

-- Instalar o docker. Foi utilizada a documentacao

https://phoenixnap.com/kb/how-to-install-docker-on-ubuntu-18-04

# sudo apt-get update
# sudo apt-get install apt-transport-https ca-certificates curl software-properties-common
# sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add –
# sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu  $(lsb_release -cs)  stable"
# sudo apt-get update
# sudo apt-get install docker-ce

-> para testar
# systemctl status docker


Obs.....

- Instalando o NGinx 
https://www.digitalocean.com/community/tutorials/como-instalar-o-nginx-no-ubuntu-18-04-pt

- Instalando o Java
# sudo apt-get install openjdk-8-jdk
# java -version

- Instalando o Maven

# sudo apt install maven
# mvn -version