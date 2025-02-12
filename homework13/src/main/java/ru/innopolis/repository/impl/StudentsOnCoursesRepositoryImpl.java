package ru.innopolis.repository.impl;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.innopolis.config.JdbcTemplateConfig;
import ru.innopolis.entity.StudentsOnCourses;
import ru.innopolis.repository.StudentsOnCoursesRepository;

import java.util.List;


@Repository
public class StudentsOnCoursesRepositoryImpl implements StudentsOnCoursesRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate = JdbcTemplateConfig.namedParameterJdbcTemplate();


    private static final String CREATE = """
                INSERT INTO studentsOnCourses (id, studentId, courseId)
                VALUES (:id, :studentId, :courseId)
            """;
    private static final String FIND_BY_ID = """
                SELECT
                    *
                FROM studentsOnCourses
                WHERE id = :id
            """;


    @Override
    public StudentsOnCourses createStudentsOnCourses(Long id, Long studentId, Long courseId) {
        StudentsOnCourses studentsOnCoursesParam = new StudentsOnCourses(id, studentId, courseId);
        SqlParameterSource namedParameterStudent = new BeanPropertySqlParameterSource(studentsOnCoursesParam);

        if (findByIdStudentsOnCourses(id).isEmpty()) {
            namedParameterJdbcTemplate.update(CREATE, namedParameterStudent);
            return findByIdStudentsOnCourses(id).get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<StudentsOnCourses> findByIdStudentsOnCourses(Long id) {
        SqlParameterSource namedParameterStudent = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.query(FIND_BY_ID, namedParameterStudent, studentsOnCoursesRowMapper);
    }


    private static final RowMapper<StudentsOnCourses> studentsOnCoursesRowMapper = (row, rowNumber) -> {
        Long id = row.getLong("id");
        Long studentId = row.getLong("studentId");
        Long courseId = row.getLong("courseId");

        return new StudentsOnCourses(id, studentId, courseId);
    };
}
