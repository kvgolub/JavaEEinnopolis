package ru.innopolis.service.module;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.innopolis.entity.BusStop;
import ru.innopolis.repository.BusStopRepository;
import ru.innopolis.service.impl.BusStopServiceImpl;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import static ru.innopolis.utils.BusStopVariables.*;


@ExtendWith(MockitoExtension.class)
class BusStopServiceTest {

    @Mock
    private BusStopRepository busStopRepository;

    @InjectMocks
    private BusStopServiceImpl busStopService;


    @Test
    void createBusStop() {
        Mockito.when(busStopRepository.save(Mockito.any(BusStop.class))).thenReturn(create);
        var response = busStopService.createBusStop(createRequest);

        assertThat("Тестирование \"Создание записи Автобусная остановка\"", response.getNameStop(), equalTo(createResponse.getNameStop()));
    }

    @Test
    void findByIdBusStop() {
        Mockito.when(busStopRepository.findById(1L)).thenReturn(Optional.of(find));
        Mockito.when(busStopRepository.findById(100L)).thenReturn(Optional.empty());
        var response = busStopService.findByIdBusStop(1L);
        var response2 = busStopService.findByIdBusStop(100L);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Поиск записи Автобусная остановка - запись есть\"", response.orElseThrow().getId(), equalTo(Optional.of(findResponse).orElseThrow().getId())),
                () -> assertThat("Тестирование \"Поиск записи Автобусная остановка - записи нет\"", response2, equalTo(Optional.empty()))
        );
    }

    @Test
    void findAllBusStops() {
        Mockito.when(busStopRepository.findAll()).thenReturn(findAll);
        var response = busStopService.findAllBusStops();

        assertThat("Тестирование \"Поиск всех записей Автобусная остановка - количество\"", response.size(), equalTo(2));
        assertThat("Тестирование \"Поиск всех записей Автобусная остановка - первая запись\"", response.get(0).getId(), equalTo(findAllResponse.get(0).getId()));
    }

    @Test
    void updateBusStop() {
        Mockito.when(busStopRepository.findById(1L)).thenReturn(Optional.of(find));
        Mockito.when(busStopRepository.findById(100L)).thenReturn(Optional.empty());
        Mockito.when(busStopRepository.save(Mockito.any(BusStop.class))).thenReturn(update);
        var response = busStopService.updateBusStop(1L, updateRequest);
        var response2 = busStopService.updateBusStop(100L, updateRequest);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Обновление записи Автобусная остановка - запись есть\"", response.orElseThrow().getNameStop(), equalTo(Optional.of(updateResponse).orElseThrow().getNameStop())),
                () -> assertThat("Тестирование \"Обновление записи Автобусная остановка - записи нет\"", response2, equalTo(Optional.empty()))
        );
    }

    @Test
    void deleteByIdBusStop() {
        Mockito.when(busStopRepository.deleteByIdAsSoft(1L)).thenReturn(1);
        Mockito.when(busStopRepository.deleteByIdAsSoft(100L)).thenReturn(0);
        var response = busStopService.deleteByIdBusStop(1L);
        var response2 = busStopService.deleteByIdBusStop(100L);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Удаление записи Автобусная остановка - запись есть\"", response, is(true)),
                () -> assertThat("Тестирование \"Удаление записи Автобусная остановка - записи нет\"", response2, is(false))
        );
    }

    @Test
    void deleteAllBusStops() {
        Mockito.when(busStopRepository.deleteAllAsSoft()).thenReturn(11);
        var response = busStopService.deleteAllBusStops();

        assertThat("Тестирование \"Удаление всех записей Автобусная остановка\"", response, equalTo(11));
    }
}
