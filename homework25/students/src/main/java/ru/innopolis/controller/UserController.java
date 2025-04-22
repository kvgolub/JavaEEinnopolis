package ru.innopolis.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innopolis.dto.user.UserCreateRequest;
import ru.innopolis.dto.user.UserAllCoursesResponse;
import ru.innopolis.dto.user.UserStudentResponse;


@RequestMapping("/api/v1/user")
public interface UserController {

    @PostMapping("/registration")
    ResponseEntity<Boolean> createAccount(@Valid @RequestBody UserCreateRequest userCreateRequest);

    @GetMapping("/courses")
    ResponseEntity<UserAllCoursesResponse> findAllCoursesForUser(@AuthenticationPrincipal UserDetails userDetails);

    @GetMapping("/student")
    ResponseEntity<UserStudentResponse> findStudentForUser(@AuthenticationPrincipal UserDetails userDetails);

}
