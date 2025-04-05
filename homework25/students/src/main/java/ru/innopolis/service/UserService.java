package ru.innopolis.service;

import ru.innopolis.dto.user.UserCreateRequest;
import ru.innopolis.dto.user.UserAllCoursesResponse;
import ru.innopolis.dto.user.UserStudentResponse;


public interface UserService {

    Boolean createAccount(UserCreateRequest userCreateRequest);
    UserAllCoursesResponse findAllCoursesForUser(String username);
    UserStudentResponse findStudentForUser(String username);

}
