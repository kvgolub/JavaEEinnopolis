FROM maven:latest AS build
COPY pom.xml /home/figures/
COPY src /home/figures/src/
WORKDIR /home/figures
RUN mvn clean package

FROM amazoncorretto:21
ENV JAR_FILE=/home/figures/target/*.jar
ENV FIGURES_TXT_FILE=/home/figures/src/main/resources/figures.txt
COPY --from=build ${JAR_FILE} /home/figures/app.jar
COPY --from=build $FIGURES_TXT_FILE /home/figures/
