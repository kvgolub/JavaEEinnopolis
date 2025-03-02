package ru.innopolis.service.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.innopolis.dto.student.StudentRequest;
import ru.innopolis.dto.student.StudentResponse;
import ru.innopolis.entity.Student;
import ru.innopolis.repository.StudentRepository;
import ru.innopolis.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentResponse createStudent(StudentRequest studentRequest) {
        Student studentInDb = new Student(
                null,
                studentRequest.getSurname(),
                studentRequest.getName(),
                studentRequest.getPatronymic(),
                studentRequest.getAge(),
                studentRequest.getEmail()
        );

        Student studentFromDb = studentRepository.save(studentInDb);

        return new StudentResponse(
                studentFromDb.getId(),
                studentFromDb.getSurname(),
                studentFromDb.getName(),
                studentFromDb.getPatronymic(),
                studentRequest.getAge(),
                studentFromDb.getEmail()
        );
    }

    @Override
    public Optional<StudentResponse> findByIdStudent(Long id) {
        try {
            Student studentFromDb = studentRepository.findById(id).orElseThrow();

            return Optional.of(
                    new StudentResponse(
                            studentFromDb.getId(),
                            studentFromDb.getSurname(),
                            studentFromDb.getName(),
                            studentFromDb.getPatronymic(),
                            studentFromDb.getAge(),
                            studentFromDb.getEmail()
                    )
            );
        } catch (EmptyResultDataAccessException | NoSuchElementException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<StudentResponse> findAllStudents() {
        List<Student> studentFromDb = studentRepository.findAll();
        List<StudentResponse> response = new ArrayList<>();
        studentFromDb.forEach(student -> response.add(
                new StudentResponse(
                        student.getId(),
                        student.getSurname(),
                        student.getName(),
                        student.getPatronymic(),
                        student.getAge(),
                        student.getEmail()
                )
        ));

        return response;
    }

    @Override
    public Optional<StudentResponse> updateStudent(Long id, StudentRequest studentRequest) {
        try {
            Student getStudent = studentRepository.findById(id).orElseThrow();

            if (getStudent.getId() != null) {
                Student studentNew = new Student(
                        id,
                        studentRequest.getSurname(),
                        studentRequest.getName(),
                        studentRequest.getPatronymic(),
                        studentRequest.getAge(),
                        studentRequest.getEmail()
                );

                Student studentFromDb = studentRepository.save(studentNew);

                return Optional.of(
                        new StudentResponse(
                                studentFromDb.getId(),
                                studentFromDb.getSurname(),
                                studentFromDb.getName(),
                                studentFromDb.getPatronymic(),
                                studentRequest.getAge(),
                                studentFromDb.getEmail()
                        )
                );
            } else {
                return Optional.empty();
            }
        } catch (EmptyResultDataAccessException | NoSuchElementException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteByIdStudent(Long id) {
        studentRepository.deleteById(id);
        return true;
    }

    @Override
    public int deleteAllStudents() {
        studentRepository.deleteAll();
        return 1;
    }

    @Override
    public List<StudentResponse> getStudentOver30yearsOld(Integer age) {
        List<Student> studentFromDb = studentRepository.queryStudentsByAgeGreaterThanEqual(age);
        List<StudentResponse> response = new ArrayList<>();
        studentFromDb.forEach(student -> response.add(
                new StudentResponse(
                        student.getId(),
                        student.getSurname(),
                        student.getName(),
                        student.getPatronymic(),
                        student.getAge(),
                        student.getEmail()
                )
        ));

        return response;
    }

}
