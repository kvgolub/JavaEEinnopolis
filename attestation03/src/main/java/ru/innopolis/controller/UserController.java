package ru.innopolis.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innopolis.dto.user.UserResponse;

import java.util.List;
import java.util.Optional;


@RequestMapping("/api/v1/bus_station/user")
public interface UserController {

    @GetMapping("/{username}")
    ResponseEntity<Optional<UserResponse>> findByIdUser(@Valid @PathVariable("username") String userName);

    @GetMapping("/list")
    ResponseEntity<List<UserResponse>> findAllUsers();

}
