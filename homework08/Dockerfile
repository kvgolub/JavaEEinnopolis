FROM maven:latest
COPY pom.xml /home/strings/
COPY src /home/strings/src/
WORKDIR /home/strings
RUN mvn clean package
