package ru.innopolis.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innopolis.dto.user.UserRequest;
import ru.innopolis.dto.user.UserResponse;


@RequestMapping("/api/v1/user")
public interface UserController {

    @GetMapping("/account")
    ResponseEntity<UserResponse> getCoursesForAccount(@AuthenticationPrincipal UserDetails userDetails);

    @PostMapping("/registration")
    ResponseEntity<Boolean> createAccount(@Valid @RequestBody UserRequest userRequest);

}
