package ru.innopolis.service;

import ru.innopolis.dto.user.UserRequest;
import ru.innopolis.dto.user.UserResponse;


public interface UserService {

    UserResponse findOneStudentAllCourses(String username);

    Boolean createAccount(UserRequest userRequest);

}
