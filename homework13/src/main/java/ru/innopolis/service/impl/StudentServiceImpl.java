package ru.innopolis.service.impl;

import org.springframework.stereotype.Service;
import ru.innopolis.entity.Student;
import ru.innopolis.repository.impl.StudentRepositoryImpl;
import ru.innopolis.service.StudentService;

import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepositoryImpl studentRepository;

    public StudentServiceImpl(StudentRepositoryImpl studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.createStudent(student);
    }

    @Override
    public Student findByIdStudent(Long id) {
        try {
            Student getStudent = studentRepository.findByIdStudent(id);

            return studentRepository.findByIdStudent(getStudent.getId());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAllStudents();
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        try {
            Student studentUpdate = studentRepository.findByIdStudent(id);
            studentUpdate.setSurname(student.getSurname());
            studentUpdate.setName(student.getName());
            studentUpdate.setPatronymic(student.getPatronymic());
            studentUpdate.setEmail(student.getEmail());

            return studentRepository.updateStudent(studentUpdate);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean deleteByIdStudent(Long id) {
        try {
            Student getStudent = studentRepository.findByIdStudent(id);

            return studentRepository.deleteByIdStudent(getStudent.getId()) == 1;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int deleteAllStudents() {
        return studentRepository.deleteAllStudents();
    }

}
