package ru.innopolis.config;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


public class JdbcTemplateConfig {
    public static JdbcTemplate jdbcTemplate() {
        var driver = new DriverManagerDataSource("jdbc:postgresql://localhost:9002/demo", "postgres", "postgres");
        driver.setDriverClassName("org.postgresql.Driver");
        driver.setSchema("bookings");
        return new JdbcTemplate(driver);
    }
}
