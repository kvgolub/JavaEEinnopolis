package ru.innopolis.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;
import ru.innopolis.controller.UserController;
import ru.innopolis.dto.user.UserRequest;
import ru.innopolis.dto.user.UserResponse;
import ru.innopolis.service.UserService;


@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;


    @Override
    public ResponseEntity<UserResponse> getCoursesForAccount(UserDetails userDetails) {
        UserResponse userResponse = userService.findOneStudentAllCourses(userDetails.getUsername());

        if (userResponse != null) {
            return ResponseEntity.ok().body(userResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Override
    public ResponseEntity<Boolean> createAccount(UserRequest userRequest) {
        Boolean createUser = userService.createAccount(userRequest);

        if (createUser) {
            return ResponseEntity.status(HttpStatus.CREATED).body(true);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(false);
        }
    }

}
