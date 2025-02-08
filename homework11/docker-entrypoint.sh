#!/bin/sh

#mvn clean liquibase:update

#mvn clean package

mvn clean -P production spring-boot:run
