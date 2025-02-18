package ru.innopolis.config;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


public class JdbcTemplateConfig {
    public static JdbcTemplate jdbcTemplate() {
        var driver = new DriverManagerDataSource("jdbc:postgresql://localhost:9001/courses", "postgres", "postgres");
        driver.setDriverClassName("org.postgresql.Driver");
        driver.setSchema("public");
        return new JdbcTemplate(driver);
    }

    public static NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        var driver = new DriverManagerDataSource("jdbc:postgresql://localhost:9001/courses", "postgres", "postgres");
        driver.setDriverClassName("org.postgresql.Driver");
        driver.setSchema("public");
        return new NamedParameterJdbcTemplate(driver);
    }
}
