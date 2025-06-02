package ru.innopolis.service.module;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.innopolis.repository.AuthorityRepository;
import ru.innopolis.repository.UserRepository;
import ru.innopolis.service.impl.UserServiceImpl;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import static ru.innopolis.utils.UserVariables.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthorityRepository authorityRepository;

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    void findByIdUser() {
        Mockito.when(userRepository.findById("user")).thenReturn(Optional.of(find));
        Mockito.when(userRepository.findById("viewer")).thenReturn(Optional.empty());
        var response = userService.findByIdUser("user");
        var response2 = userService.findByIdUser("viewer");

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Поиск записи Пользователь - запись есть\"", response.orElseThrow().getUsername(), equalTo(Optional.of(findResponse).orElseThrow().getUsername())),
                () -> assertThat("Тестирование \"Поиск записи Пользователь - записи нет\"", response2, equalTo(Optional.empty()))
        );
    }

    @Test
    void findAllUsers() {
        Mockito.when(userRepository.findAll()).thenReturn(findAll);
        var response = userService.findAllUsers();

        assertThat("Тестирование \"Поиск всех записей Пользователь - количество\"", response.size(), equalTo(2));
        assertThat("Тестирование \"Поиск всех записей Пользователь - первая запись\"", response.get(0).getUsername(), equalTo(findAllResponse.get(0).getUsername()));
    }
}
