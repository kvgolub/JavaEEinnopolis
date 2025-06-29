package ru.innopolis.service.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;
import ru.innopolis.client.courses.impl.CoursesClientImpl;
import ru.innopolis.dto.student.StudentResponse;
import ru.innopolis.dto.user.UserCreateRequest;
import ru.innopolis.dto.user.UserAllCoursesResponse;
import ru.innopolis.dto.studentCourse.OneStudentManyCoursesResponse;
import ru.innopolis.dto.user.UserStudentResponse;
import ru.innopolis.entity.Student;
import ru.innopolis.entity.StudentCourse;
import ru.innopolis.repository.StudentCourseRepository;
import ru.innopolis.repository.StudentRepository;
import ru.innopolis.service.UserService;

import java.util.*;


@Service
public class UserServiceImpl implements UserService {

    private final StudentRepository studentRepository;
    private final CoursesClientImpl coursesClient;
    private final StudentCourseRepository studentCourseRepository;

    private final PasswordEncoder passwordEncoder;
    private final JdbcUserDetailsManager jdbcUserDetailsManager;

    public UserServiceImpl(StudentRepository studentRepository, CoursesClientImpl coursesClient, StudentCourseRepository studentCourseRepository, PasswordEncoder passwordEncoder, JdbcUserDetailsManager jdbcUserDetailsManager) {
        this.studentRepository = studentRepository;
        this.coursesClient = coursesClient;
        this.studentCourseRepository = studentCourseRepository;
        this.passwordEncoder = passwordEncoder;
        this.jdbcUserDetailsManager = jdbcUserDetailsManager;
    }


    @Override
    public Boolean createAccount(UserCreateRequest userCreateRequest) {
        try {
            Student getStudent = studentRepository.findStudentByEmail(userCreateRequest.getStudentRequest().getEmail());

            if (getStudent == null) {
                Student studentNew = new Student(
                        null,
                        userCreateRequest.getStudentRequest().getSurname(),
                        userCreateRequest.getStudentRequest().getName(),
                        userCreateRequest.getStudentRequest().getPatronymic(),
                        userCreateRequest.getStudentRequest().getAge(),
                        userCreateRequest.getStudentRequest().getEmail()
                );

                studentRepository.save(studentNew);

                jdbcUserDetailsManager.createUser(
                        new User(
                                userCreateRequest.getUsername(),
                                passwordEncoder.encode(userCreateRequest.getPassword()),
                                List.of(new SimpleGrantedAuthority(userCreateRequest.getAuthority()))
                        )
                );

                return true;
            } else {
                return false;
            }
        } catch (EmptyResultDataAccessException | NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public UserAllCoursesResponse findAllCoursesForUser(String username) {
        try {
            Long id = studentRepository.findStudentByEmail(username + "@mail.ru").getId();
            List<StudentCourse> studentCourseList = studentCourseRepository.findStudentCourseByStudentId(id);

            UserAllCoursesResponse userAllCoursesResponse = new UserAllCoursesResponse();
            userAllCoursesResponse.setCourseResponseList(getOneStudentManyCoursesList(studentCourseList).getCourses());

            return userAllCoursesResponse;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public UserStudentResponse findStudentForUser(String username) {
        try {
            Student student = studentRepository.findStudentByEmail(username + "@mail.ru");
            UserStudentResponse userStudentResponse = new UserStudentResponse();

            userStudentResponse.setStudentResponse(new StudentResponse(
                    student.getId(),
                    student.getSurname(),
                    student.getName(),
                    student.getPatronymic(),
                    student.getAge(),
                    student.getEmail()
            ));

            return userStudentResponse;
        } catch (EmptyResultDataAccessException | NoSuchElementException e) {
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
