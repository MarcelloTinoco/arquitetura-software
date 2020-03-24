mvn clean package install -DskipTests && \
sudo docker build -t foo/foo-service:1.0 .