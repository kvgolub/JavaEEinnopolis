package ru.innopolis.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.innopolis.config.JdbcTemplateConfig;
import ru.innopolis.entity.Course;
import ru.innopolis.repository.CourseRepository;

import java.util.List;


@Repository
public class CourseRepositoryImpl implements CourseRepository {

    private final JdbcTemplate jdbcTemplate = JdbcTemplateConfig.jdbcTemplate();
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate = JdbcTemplateConfig.namedParameterJdbcTemplate();


    private static final String CREATE = """
                INSERT INTO course (name, date, active)
                VALUES (:name, :date::timestamp, :active)
                RETURNING *
            """;
    private static final String FIND_BY_ID = """
                SELECT
                    *
                FROM course
                WHERE id = :id
            """;
    private static final String FIND_ALL = """
                SELECT
                    *
                FROM course
            """;
    private static final String UPDATE = """
                UPDATE course
                SET name = :name,
                    date = :date::timestamp,
                    active = :active
                WHERE id = :id
                RETURNING *
            """;
    private static final String DELETE_BY_ID = """
                DELETE FROM course
                WHERE id = :id
            """;
    private static final String DELETE_ALL = """
                DELETE FROM course
            """;


    @Override
    public Course createCourse(Course course) {
        SqlParameterSource namedParameterCourse = new BeanPropertySqlParameterSource(course);
        return namedParameterJdbcTemplate.queryForObject(CREATE, namedParameterCourse, courseRowMapper) ;
    }

    @Override
    public Course findByIdCourse(Long id) {
        SqlParameterSource namedParameterId = new MapSqlParameterSource("id", id);

        return namedParameterJdbcTemplate.queryForObject(FIND_BY_ID, namedParameterId, courseRowMapper);
    }

    @Override
    public List<Course> findAllCourses() {
        return jdbcTemplate.query(FIND_ALL, courseRowMapper);
    }

    @Override
    public Course updateCourse(Course course) {

        SqlParameterSource namedParameterCourse = new BeanPropertySqlParameterSource(course);
        return namedParameterJdbcTemplate.queryForObject(UPDATE, namedParameterCourse, courseRowMapper);
    }

    @Override
    public int deleteByIdCourse(Long id) {
        SqlParameterSource namedParameterId = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.update(DELETE_BY_ID, namedParameterId);
    }

    @Override
    public int deleteAllCourses() {
        return jdbcTemplate.update(DELETE_ALL);
    }


    private static final RowMapper<Course> courseRowMapper = (row, rowNumber) -> {
        Long id = row.getLong("id");
        String name = row.getString("name");
        String date = row.getString("date");
        Boolean active = row.getBoolean("active");

        return new Course(id, name, date, active);
    };

}
