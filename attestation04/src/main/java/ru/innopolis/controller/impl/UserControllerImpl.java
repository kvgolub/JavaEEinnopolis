package ru.innopolis.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.innopolis.controller.UserController;
import ru.innopolis.dto.user.UserResponse;
import ru.innopolis.service.UserService;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;


    @Override
    public ResponseEntity<Optional<UserResponse>> findByIdUser(String userName) {
        Optional<UserResponse> userResponse = userService.findByIdUser(userName);

        return userResponse.isPresent()
                ? ResponseEntity.ok().body(userResponse)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(userResponse);
    }

    @Override
    public ResponseEntity<List<UserResponse>> findAllUsers() {
        List<UserResponse> userResponseList = userService.findAllUsers();

        return !userResponseList.isEmpty()
                ? ResponseEntity.ok().body(userResponseList)
                : ResponseEntity.noContent().build();
    }
}
