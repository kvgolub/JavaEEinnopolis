FROM maven:latest AS build
COPY pom.xml /home/collections/
COPY src /home/collections/src/
WORKDIR /home/collections
RUN mvn clean package
