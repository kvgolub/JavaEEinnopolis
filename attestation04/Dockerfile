FROM maven:latest as mav
COPY pom.xml /home/bus_station/
COPY src /home/bus_station/src/
COPY flyway.conf /home/bus_station/
COPY docker-entrypoint.sh /home/bus_station/
WORKDIR /home/bus_station
