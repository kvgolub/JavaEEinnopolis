package ru.innopolis.service.impl;

import org.springframework.stereotype.Service;
import ru.innopolis.client.courses.impl.CoursesClientImpl;
import ru.innopolis.dto.account.AccountResponse;
import ru.innopolis.dto.studentCourse.OneStudentManyCoursesResponse;
import ru.innopolis.entity.Student;
import ru.innopolis.entity.StudentCourse;
import ru.innopolis.repository.StudentCourseRepository;
import ru.innopolis.repository.StudentRepository;
import ru.innopolis.service.AccountService;

import java.util.*;


@Service
public class AccountServiceImpl implements AccountService {

    private final StudentRepository studentRepository;
    private final CoursesClientImpl coursesClient;
    private final StudentCourseRepository studentCourseRepository;

    public AccountServiceImpl(StudentRepository studentRepository, CoursesClientImpl coursesClient, StudentCourseRepository studentCourseRepository) {
        this.studentRepository = studentRepository;
        this.coursesClient = coursesClient;
        this.studentCourseRepository = studentCourseRepository;
    }


    @Override
    public AccountResponse findOneStudentAllCourses(String username) {
        try {
            Long id = studentRepository.findStudentByEmail(username).getId();
            List<StudentCourse> studentCourseList = studentCourseRepository.findStudentCourseByStudentId(id);

            AccountResponse accountResponse = new AccountResponse();
            accountResponse.setCourseResponseList(getOneStudentManyCoursesList(studentCourseList).getCourses());

            return accountResponse;
        } catch (Exception e) {
            return null;
        }
    }

    private OneStudentManyCoursesResponse getOneStudentManyCoursesList(List<StudentCourse> studentCourseList) {
        Student student = studentRepository.findById(studentCourseList.get(0).getId()).orElseThrow();

        OneStudentManyCoursesResponse oneStudentManyCoursesResponse = new OneStudentManyCoursesResponse();
        oneStudentManyCoursesResponse.setId(student.getId());
        oneStudentManyCoursesResponse.setSurname(student.getSurname());
        oneStudentManyCoursesResponse.setName(student.getName());
        oneStudentManyCoursesResponse.setPatronymic(student.getPatronymic());
        oneStudentManyCoursesResponse.setAge(student.getAge());
        oneStudentManyCoursesResponse.setEmail(student.getEmail());

        Set<Long> coursesID = new HashSet<>();
        studentCourseList.forEach(studentCourse -> {
            if(Objects.equals(studentCourse.getStudentId(), student.getId())) {
                coursesID.add(studentCourse.getCourseId());
            }
        });
        oneStudentManyCoursesResponse.setCourses(coursesClient.getListCoursesByIds(coursesID));

        return oneStudentManyCoursesResponse;
    }

}
