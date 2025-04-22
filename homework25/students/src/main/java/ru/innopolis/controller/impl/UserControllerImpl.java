package ru.innopolis.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;
import ru.innopolis.controller.UserController;
import ru.innopolis.dto.user.UserCreateRequest;
import ru.innopolis.dto.user.UserAllCoursesResponse;
import ru.innopolis.dto.user.UserStudentResponse;
import ru.innopolis.service.UserService;


@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;


    @Override
    public ResponseEntity<Boolean> createAccount(UserCreateRequest userCreateRequest) {
        Boolean createUser = userService.createAccount(userCreateRequest);

        if (createUser) {
            return ResponseEntity.status(HttpStatus.CREATED).body(true);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(false);
        }
    }

    @Override
    public ResponseEntity<UserAllCoursesResponse> findAllCoursesForUser(UserDetails userDetails) {
        UserAllCoursesResponse userAllCoursesResponse = userService.findAllCoursesForUser(userDetails.getUsername());

        if (userAllCoursesResponse != null) {
            return ResponseEntity.ok().body(userAllCoursesResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Override
    public ResponseEntity<UserStudentResponse> findStudentForUser(UserDetails userDetails) {
        UserStudentResponse userStudentResponse = userService.findStudentForUser(userDetails.getUsername());

        if (userStudentResponse != null) {
            return ResponseEntity.status(HttpStatus.OK).body(userStudentResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
