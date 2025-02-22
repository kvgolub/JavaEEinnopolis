package ru.innopolis.service.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.innopolis.dto.student.StudentRequest;
import ru.innopolis.dto.student.StudentResponse;
import ru.innopolis.entity.Student;
import ru.innopolis.repository.impl.StudentRepositoryImpl;
import ru.innopolis.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepositoryImpl studentRepository;

    public StudentServiceImpl(StudentRepositoryImpl studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentResponse createStudent(StudentRequest studentRequest) {
        Student studentInDb = new Student(
                null,
                studentRequest.getSurname(),
                studentRequest.getName(),
                studentRequest.getPatronymic(),
                studentRequest.getEmail()
        );

        Student studentFromDb = studentRepository.createStudent(studentInDb);

        return new StudentResponse(
                studentFromDb.getId(),
                studentFromDb.getSurname(),
                studentFromDb.getName(),
                studentFromDb.getPatronymic(),
                studentFromDb.getEmail()
        );
    }

    @Override
    public Optional<StudentResponse> findByIdStudent(Long id) {
        try {
            Student studentFromDb = studentRepository.findByIdStudent(id);

            return Optional.of(
                    new StudentResponse(
                        studentFromDb.getId(),
                        studentFromDb.getSurname(),
                        studentFromDb.getName(),
                        studentFromDb.getPatronymic(),
                        studentFromDb.getEmail()
                    )
            );
        } catch (EmptyResultDataAccessException | NoSuchElementException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<StudentResponse> findAllStudents() {
        List<Student> studentFromDb = studentRepository.findAllStudents();
        List<StudentResponse> response = new ArrayList<>();
        studentFromDb.forEach(student -> response.add(
                new StudentResponse(
                        student.getId(),
                        student.getSurname(),
                        student.getName(),
                        student.getPatronymic(),
                        student.getEmail()
                )
        ));

        return response;
    }

    @Override
    public Optional<StudentResponse> updateStudent(Long id, StudentRequest studentRequest) {
        try {
            Student getStudent = studentRepository.findByIdStudent(id);

            if (getStudent.getId() != null) {
                Student studentNew = new Student(
                        id,
                        studentRequest.getSurname(),
                        studentRequest.getName(),
                        studentRequest.getPatronymic(),
                        studentRequest.getEmail()
                );

                Student studentFromDb = studentRepository.updateStudent(studentNew);

                return Optional.of(
                        new StudentResponse(
                                studentFromDb.getId(),
                                studentFromDb.getSurname(),
                                studentFromDb.getName(),
                                studentFromDb.getPatronymic(),
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
        return studentRepository.deleteByIdStudent(id) == 1;
    }

    @Override
    public int deleteAllStudents() {
        return studentRepository.deleteAllStudents();
    }

}
