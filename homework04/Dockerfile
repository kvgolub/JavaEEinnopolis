FROM amazoncorretto:21
LABEL authors="k.golub"
COPY db-api.jar db-api.jar
COPY application.properties application.properties
ENTRYPOINT ["java", "-jar", "db-api.jar"]
EXPOSE 8080