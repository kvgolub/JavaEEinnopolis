package ru.innopolis.utils;

import ru.innopolis.dto.bus.BusResponse;
import ru.innopolis.dto.schedule.ScheduleResponse;
import ru.innopolis.dto.user.UserResponse;
import ru.innopolis.entity.User;

import java.util.ArrayList;
import java.util.List;


public class UserVariables {

    // find
    public static final User find = new User("user", "$2a$04$W6JjJyBbrKjMkqEc5oUG8OuR.VdRg6d5VGdjsryLSuWELio.4/Jhu", true);
    public static final UserResponse findResponse = new UserResponse("user", "$2a$04$W6JjJyBbrKjMkqEc5oUG8OuR.VdRg6d5VGdjsryLSuWELio.4/Jhu", true, List.of("USER"));
    public static final String findResponseJson = """
                            {
                            	"username": "user",
                                "password": "$2a$04$W6JjJyBbrKjMkqEc5oUG8OuR.VdRg6d5VGdjsryLSuWELio.4/Jhu",
                                "enabled": true,
                                "authorities": [
                                    "USER"
                                ]
                            }
                        """;


    // findAll
    public static final List<User> findAll = List.of(
            new User("user", "$2a$04$W6JjJyBbrKjMkqEc5oUG8OuR.VdRg6d5VGdjsryLSuWELio.4/Jhu", true),
            new User("admin", "$2a$04$hOkbGUaPwRKAYb7sVqI4zuEYAnwPENWsW0YUmwX9JOye/5uNVFPdS", true)
    );
    public static final List<UserResponse>findAllResponse = List.of(
            new UserResponse("user", "$2a$04$W6JjJyBbrKjMkqEc5oUG8OuR.VdRg6d5VGdjsryLSuWELio.4/Jhu", true, List.of("USER")),
            new UserResponse("admin", "$2a$04$hOkbGUaPwRKAYb7sVqI4zuEYAnwPENWsW0YUmwX9JOye/5uNVFPdS", true, List.of("ADMIN"))
    );
    public static final List<UserResponse> findAllResponseEmpty = new ArrayList<>();
}
