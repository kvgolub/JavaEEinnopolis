package ru.innopolis.service.module;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.innopolis.entity.Route;
import ru.innopolis.exception.InvalidFileTypeException;
import ru.innopolis.repository.RouteRepository;
import ru.innopolis.service.impl.RouteServiceImpl;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import static ru.innopolis.utils.BusVariables.uploadFileNull;
import static ru.innopolis.utils.RouteVariables.*;


@ExtendWith(MockitoExtension.class)
class RouteServiceTest {

    @Mock
    private RouteRepository routeRepository;

    @InjectMocks
    private RouteServiceImpl routeService;


    @Test
    void createRoute() {
        Mockito.when(routeRepository.save(Mockito.any(Route.class))).thenReturn(create);
        var response = routeService.createRoute(createRequest);

        assertThat("Тестирование \"Создание записи Маршрут\"", response.getNumber(), equalTo(createResponse.getNumber()));
    }

    @Test
    void findByIdRoute() {
        Mockito.when(routeRepository.findById(1L)).thenReturn(Optional.of(find));
        Mockito.when(routeRepository.findById(100L)).thenReturn(Optional.empty());
        var response = routeService.findByIdRoute(1L);
        var response2 = routeService.findByIdRoute(100L);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Поиск записи Маршрут - запись есть\"", response.orElseThrow().getId(), equalTo(Optional.of(findResponse).orElseThrow().getId())),
                () -> assertThat("Тестирование \"Поиск записи Маршрут - записи нет\"", response2, equalTo(Optional.empty()))
        );
    }

    @Test
    void findAllRoutes() {
        Mockito.when(routeRepository.findAll()).thenReturn(findAll);
        var response = routeService.findAllRoutes();

        assertThat("Тестирование \"Поиск всех записей Маршрут - количество\"", response.size(), equalTo(2));
        assertThat("Тестирование \"Поиск всех записей Маршрут - первая запись\"", response.get(0).getId(), equalTo(findAllResponse.get(0).getId()));
    }

    @Test
    void updateRoute() {
        Mockito.when(routeRepository.findById(1L)).thenReturn(Optional.of(find));
        Mockito.when(routeRepository.findById(100L)).thenReturn(Optional.empty());
        Mockito.when(routeRepository.save(Mockito.any(Route.class))).thenReturn(update);
        var response = routeService.updateRoute(1L, updateRequest);
        var response2 = routeService.updateRoute(100L, updateRequest);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Обновление записи Маршрут - запись есть\"", response.orElseThrow().getNumber(), equalTo(Optional.of(updateResponse).orElseThrow().getNumber())),
                () -> assertThat("Тестирование \"Обновление записи Маршрут - записи нет\"", response2, equalTo(Optional.empty()))
        );
    }

    @Test
    void deleteByIdRoute() {
        Mockito.when(routeRepository.deleteByIdAsSoft(1L)).thenReturn(1);
        Mockito.when(routeRepository.deleteByIdAsSoft(100L)).thenReturn(0);
        var response = routeService.deleteByIdRoute(1L);
        var response2 = routeService.deleteByIdRoute(100L);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Удаление записи Маршрут - запись есть\"", response, is(true)),
                () -> assertThat("Тестирование \"Удаление записи Маршрут - записи нет\"", response2, is(false))
        );
    }

    @Test
    void deleteAllRoutes() {
        Mockito.when(routeRepository.deleteAllAsSoft()).thenReturn(11);
        var response = routeService.deleteAllRoutes();

        assertThat("Тестирование \"Удаление всех записей Маршрут\"", response, equalTo(11));
    }

    @Test
    void uploadFileRoutes() {
        var response = routeService.uploadFileRoutes(uploadFile);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Загрузка данных для записей Маршрут\"", response, is(true)),
                () -> Assertions.assertThrows(InvalidFileTypeException.class, () -> routeService.uploadFileRoutes(uploadFileNull))
        );
    }
}
