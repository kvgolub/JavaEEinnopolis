package ru.innopolis.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import ru.innopolis.dto.user.UserResponse;
import ru.innopolis.entity.Authority;
import ru.innopolis.entity.User;
import ru.innopolis.repository.AuthorityRepository;
import ru.innopolis.repository.UserRepository;
import ru.innopolis.service.impl.UserServiceImpl;

import java.util.List;
import java.util.Optional;


@SpringBootTest(classes = {UserServiceImpl.class})
class UserServiceTest {

    @MockitoBean
    private UserRepository userRepository;

    @MockitoBean
    private AuthorityRepository authorityRepository;

    @Autowired
    private UserServiceImpl userService;


    @Test
    void findByIdUser() {
        Mockito.when(userRepository.findById(Mockito.any(String.class))).thenReturn(Optional.of(user));
        Mockito.when(authorityRepository.findAllById(Mockito.any(List.class))).thenReturn(authoritiesList);
        var response = userService.findByIdUser("user");

        Assertions.assertEquals(Optional.of(userResponse).orElseThrow().getUsername(), response.orElseThrow().getUsername());
    }

    @Test
    void findAllUsers() {
        Mockito.when(userRepository.findAll()).thenReturn(usersAll);
        Mockito.when(authorityRepository.findAll()).thenReturn(authoritiesAllList);
        var response = userService.findAllUsers();

        Assertions.assertEquals(userResponseAll.get(0).getUsername(), response.get(0).getUsername());
    }


    // find
    private final User user = new User("user", "$2a$04$W6JjJyBbrKjMkqEc5oUG8OuR.VdRg6d5VGdjsryLSuWELio.4/Jhu", true);
    private final List<Authority> authoritiesList = List.of(
            new Authority("user",  "USER")
    );
    private final UserResponse userResponse = new UserResponse("user", "$2a$04$W6JjJyBbrKjMkqEc5oUG8OuR.VdRg6d5VGdjsryLSuWELio.4/Jhu", true, List.of("USER"));


    // findAll
    private final List<User> usersAll = List.of(
            new User("user", "$2a$04$W6JjJyBbrKjMkqEc5oUG8OuR.VdRg6d5VGdjsryLSuWELio.4/Jhu", true),
            new User("admin", "$2a$04$hOkbGUaPwRKAYb7sVqI4zuEYAnwPENWsW0YUmwX9JOye/5uNVFPdS", true)
    );
    private final List<Authority> authoritiesAllList = List.of(
            new Authority("user",  "USER"),
            new Authority("admin",  "ADMIN")
    );
    private final List<UserResponse> userResponseAll = List.of(
            new UserResponse("user", "$2a$04$W6JjJyBbrKjMkqEc5oUG8OuR.VdRg6d5VGdjsryLSuWELio.4/Jhu", true, List.of("USER")),
            new UserResponse("admin", "$2a$04$hOkbGUaPwRKAYb7sVqI4zuEYAnwPENWsW0YUmwX9JOye/5uNVFPdS", true, List.of("ADMIN"))
    );

}