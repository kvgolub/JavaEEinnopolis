package ru.innopolis.repository.impl;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.innopolis.config.JdbcTemplateConfig;
import ru.innopolis.entity.StudentOnCourse;
import ru.innopolis.repository.StudentOnCourseRepository;

import java.util.List;


@Repository
public class StudentOnCourseRepositoryImpl implements StudentOnCourseRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate = JdbcTemplateConfig.namedParameterJdbcTemplate();


    private static final String CREATE = """
                INSERT INTO studentsOnCourses (studentId, courseId)
                VALUES (:studentId, :courseId)
                RETURNING *
            """;
    private static final String FIND_ALL_COURSES_FOR_STUDENT = """
                SELECT
                    *
                FROM studentsOnCourses
                WHERE studentId = :studentId
            """;


    @Override
    public StudentOnCourse createStudentOnCourse(StudentOnCourse studentOnCourse) {
        //StudentOnCourse studentOnCourseParam = new StudentOnCourse(studentId, courseId);
        SqlParameterSource namedParameterStudent = new BeanPropertySqlParameterSource(studentOnCourse);

        return namedParameterJdbcTemplate.queryForObject(CREATE, namedParameterStudent, studentsOnCoursesRowMapper);
    }

    @Override
    public List<StudentOnCourse> findAllCoursesForStudent(Long studentId) {
        SqlParameterSource namedParameterStudent = new MapSqlParameterSource("studentId", studentId);

        return namedParameterJdbcTemplate.query(FIND_ALL_COURSES_FOR_STUDENT, namedParameterStudent, studentsOnCoursesRowMapper);
    }


    private static final RowMapper<StudentOnCourse> studentsOnCoursesRowMapper = (row, rowNumber) -> {
        Long id = row.getLong("id");
        Long studentId = row.getLong("studentId");
        Long courseId = row.getLong("courseId");

        return new StudentOnCourse(id, studentId, courseId);
    };

}
