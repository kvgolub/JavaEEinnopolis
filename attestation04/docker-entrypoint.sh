#!/bin/sh

# Миграция БД
mvn clean flyway:migrate

# Запуск приложения
mvn clean spring-boot:run

# Только прогон тестов
#mvn clean test
