package ru.innopolis.service;

import ru.innopolis.dto.user.UserResponse;

import java.util.List;
import java.util.Optional;


public interface UserService {

    Optional<UserResponse> findByIdUser(String userName);
    List<UserResponse> findAllUsers();

}
