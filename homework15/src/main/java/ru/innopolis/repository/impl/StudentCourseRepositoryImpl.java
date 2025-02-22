package ru.innopolis.repository.impl;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.innopolis.config.JdbcTemplateConfig;
import ru.innopolis.entity.StudentCourse;
import ru.innopolis.repository.StudentCourseRepository;

import java.util.List;


@Repository
public class StudentCourseRepositoryImpl implements StudentCourseRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate = JdbcTemplateConfig.namedParameterJdbcTemplate();


    private static final String CREATE = """
                INSERT INTO students_courses (studentId, courseId)
                VALUES (:studentId, :courseId)
                RETURNING *
            """;
    private static final String FIND_ONE_STUDENT_ALL_COURSES = """
                SELECT
                    *
                FROM students_courses
                WHERE studentId = :studentId
            """;
    private static final String FIND_ALL_STUDENTS_ONE_COURSE = """
                SELECT
                    *
                FROM students_courses
                WHERE courseId = :courseId
            """;
    private static final String FIND_ALL_STUDENT_COURSE = """
                SELECT
                    *
                FROM students_courses
            """;


    @Override
    public StudentCourse createStudentCourse(StudentCourse studentCourse) {
        //StudentCourse studentCourseParam = new StudentCourse(studentId, courseId);
        SqlParameterSource namedParameterStudent = new BeanPropertySqlParameterSource(studentCourse);

        return namedParameterJdbcTemplate.queryForObject(CREATE, namedParameterStudent, studentCourseRowMapper);
    }

    @Override
    public List<StudentCourse> findOneStudentAllCourses(Long studentId) {
        SqlParameterSource namedParameterStudent = new MapSqlParameterSource("studentId", studentId);

        return namedParameterJdbcTemplate.query(FIND_ONE_STUDENT_ALL_COURSES, namedParameterStudent, studentCourseRowMapper);
    }

    @Override
    public List<StudentCourse> findAllStudentsOneCourse(Long courseId) {
        SqlParameterSource namedParameterStudent = new MapSqlParameterSource("courseId", courseId);

        return namedParameterJdbcTemplate.query(FIND_ALL_STUDENTS_ONE_COURSE, namedParameterStudent, studentCourseRowMapper);
    }

    @Override
    public List<StudentCourse> findAllStudentCourse() {
        return namedParameterJdbcTemplate.query(FIND_ALL_STUDENT_COURSE, studentCourseRowMapper);
    }


    private static final RowMapper<StudentCourse> studentCourseRowMapper = (row, rowNumber) -> {
        Long id = row.getLong("id");
        Long studentId = row.getLong("studentId");
        Long courseId = row.getLong("courseId");

        return new StudentCourse(id, studentId, courseId);
    };

}
