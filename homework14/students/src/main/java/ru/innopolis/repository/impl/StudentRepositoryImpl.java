package ru.innopolis.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.stereotype.Repository;
import ru.innopolis.config.JdbcTemplateConfig;
import ru.innopolis.entity.Student;
import ru.innopolis.repository.StudentRepository;

import java.util.List;


@Repository
public class StudentRepositoryImpl implements StudentRepository {

    private final JdbcTemplate jdbcTemplate = JdbcTemplateConfig.jdbcTemplate();
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate = JdbcTemplateConfig.namedParameterJdbcTemplate();


    private static final String CREATE = """
                INSERT INTO student (surname, name, patronymic, email)
                VALUES (:surname, :name, :patronymic, :email)
                RETURNING *
            """;
    private static final String FIND_BY_ID = """
                SELECT
                    *
                FROM student
                WHERE id = :id
            """;
    private static final String FIND_ALL = """
                SELECT
                    *
                FROM student
            """;
    private static final String UPDATE = """
                UPDATE student
                SET surname = :surname,
                    name = :name,
                    patronymic = :patronymic,
                    email = :email
                WHERE id = :id
                RETURNING *
            """;
    private static final String DELETE_BY_ID = """
                DELETE FROM student
                WHERE id = :id
            """;
    private static final String DELETE_ALL = """
                DELETE FROM student
            """;


    @Override
    public Student createStudent(Student student) {
        SqlParameterSource namedParameterStudent = new BeanPropertySqlParameterSource(student);
        return namedParameterJdbcTemplate.queryForObject(CREATE, namedParameterStudent, studentRowMapper);
    }

    @Override
    public Student findByIdStudent(Long id){
        SqlParameterSource namedParameterId = new MapSqlParameterSource("id", id);

        return namedParameterJdbcTemplate.queryForObject(FIND_BY_ID, namedParameterId, studentRowMapper);
    }

    @Override
    public List<Student> findAllStudents() {
        return jdbcTemplate.query(FIND_ALL, studentRowMapper);
    }

    @Override
    public Student updateStudent(Student student) {
        SqlParameterSource namedParameterStudent = new BeanPropertySqlParameterSource(student);
        return namedParameterJdbcTemplate.queryForObject(UPDATE, namedParameterStudent, studentRowMapper);
    }

    @Override
    public int deleteByIdStudent(Long id) {
        SqlParameterSource namedParameterId = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.update(DELETE_BY_ID, namedParameterId);
    }

    @Override
    public int deleteAllStudents() {
        return jdbcTemplate.update(DELETE_ALL);
    }


    private static final RowMapper<Student> studentRowMapper = (row, rowNumber) -> {
        Long id = row.getLong("id");
        String surname = row.getString("surname");
        String name = row.getString("name");
        String patronymic = row.getString("patronymic");
        String email = row.getString("email");

        return new Student(id, surname, name, patronymic, email);
    };

}
