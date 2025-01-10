FROM maven:latest
COPY pom.xml /home/collections/
COPY src /home/collections/src/
WORKDIR /home/collections
RUN mvn clean package
