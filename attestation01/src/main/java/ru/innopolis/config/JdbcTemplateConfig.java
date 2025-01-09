package ru.innopolis.config;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


public class JdbcTemplateConfig {
    public static JdbcTemplate jdbcTemplate() {
        var driver = new DriverManagerDataSource("jdbc:postgresql://localhost:5432/attestation01", "postgres", "postgres");
        driver.setDriverClassName("org.postgresql.Driver");
        driver.setSchema("bus_station");
        return new JdbcTemplate(driver);
    }
}

