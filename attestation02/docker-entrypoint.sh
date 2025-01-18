#!/bin/sh

mvn clean flyway:migrate

mvn clean package

java -jar /home/bus_station/target/attestation02-1.0-SNAPSHOT.jar > /home/bus_station/result/result.txt
