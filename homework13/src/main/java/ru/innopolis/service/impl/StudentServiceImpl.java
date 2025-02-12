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
    public boolean createStudent(Student student) {
        List<Student> getStudent = studentRepository.findByIdStudent(student.getId());

        if (getStudent.isEmpty()) {
            return studentRepository.createStudent(student) == 1;
        } else {
            return false;
        }
    }

    @Override
    public Student findByIdStudent(Long id) {
        List<Student> getStudent = studentRepository.findByIdStudent(id);

        if (!getStudent.isEmpty()) {
            return studentRepository.findByIdStudent(id).get(0);
        } else {
            return null;
        }

    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAllStudents();
    }

    @Override
    public boolean updateStudent(Long id, Student student) {
        List<Student> getStudent = studentRepository.findByIdStudent(id);

        if (!getStudent.isEmpty()) {
            Student studentNew = getStudent.get(0);
            studentNew.setSurname(student.getSurname());
            studentNew.setName(student.getName());
            studentNew.setPatronymic(student.getPatronymic());
            studentNew.setEmail(student.getEmail());

            return studentRepository.updateStudent(studentNew) == 1;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteByIdStudent(Long id) {
        List<Student> getStudent = studentRepository.findByIdStudent(id);

        if (!getStudent.isEmpty()) {
            return studentRepository.deleteByIdStudent(id) == 1;
        } else {
            return false;
        }

    }

    @Override
    public int deleteAllStudents() {
        return studentRepository.deleteAllStudents();
    }

}
