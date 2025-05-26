package ru.innopolis.service.module;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.innopolis.entity.Bus;
import ru.innopolis.exception.InvalidFileTypeException;
import ru.innopolis.repository.BusRepository;
import ru.innopolis.service.impl.BusServiceImpl;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import static ru.innopolis.utils.BusVariables.*;


@ExtendWith(MockitoExtension.class)
class BusServiceTest {

    @Mock
    private BusRepository busRepository;

    @InjectMocks
    private BusServiceImpl busService;


    @Test
    void createBus() {
        Mockito.when(busRepository.save(Mockito.any(Bus.class))).thenReturn(create);
        var response = busService.createBus(createRequest);

        assertThat("Тестирование \"Создание записи Автобус\"", response.getModel(), equalTo(createResponse.getModel()));
    }

    @Test
    void findByIdBus() {
        Mockito.when(busRepository.findById(1L)).thenReturn(Optional.of(find));
        Mockito.when(busRepository.findById(100L)).thenReturn(Optional.empty());
        var response = busService.findByIdBus(1L);
        var response2 = busService.findByIdBus(100L);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Поиск записи Автобус - запись есть\"", response.orElseThrow().getId(), equalTo(Optional.of(findResponse).orElseThrow().getId())),
                () -> assertThat("Тестирование \"Поиск записи Автобус - записи нет\"", response2, equalTo(Optional.empty()))
        );
    }

    @Test
    void findAllBuses() {
        Mockito.when(busRepository.findAll()).thenReturn(findAll);
        var response = busService.findAllBuses();

        assertThat("Тестирование \"Поиск всех записей Автобус - количество\"", response.size(), equalTo(2));
        assertThat("Тестирование \"Поиск всех записей Автобус - первая запись\"", response.get(0).getId(), equalTo(findAllResponse.get(0).getId()));
    }

    @Test
    void updateBus() {
        Mockito.when(busRepository.findById((1L))).thenReturn(Optional.of(find));
        Mockito.when(busRepository.findById((100L))).thenReturn(Optional.empty());
        Mockito.when(busRepository.save(Mockito.any(Bus.class))).thenReturn(update);
        var response = busService.updateBus(1L, updateRequest);
        var response2 = busService.updateBus(100L, updateRequest);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Обновление записи Автобус - запись есть\"", response.orElseThrow().getModel(), equalTo(Optional.of(updateResponse).orElseThrow().getModel())),
                () -> assertThat("Тестирование \"Обновление записи Автобус - записи нет\"", response2, equalTo(Optional.empty()))
        );
    }

    @Test
    void deleteByIdBus() {
        Mockito.when(busRepository.deleteByIdAsSoft(1L)).thenReturn(1);
        Mockito.when(busRepository.deleteByIdAsSoft(100L)).thenReturn(0);
        var response = busService.deleteByIdBus(1L);
        var response2 = busService.deleteByIdBus(100L);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Удаление записи Автобус - запись есть\"", response, is(true)),
                () -> assertThat("Тестирование \"Удаление записи Автобус - записи нет\"", response2, is(false))
        );
    }

    @Test
    void deleteAllBuses() {
        Mockito.when(busRepository.deleteAllAsSoft()).thenReturn(3);
        var response = busService.deleteAllBuses();

        assertThat("Тестирование \"Удаление всех записей Автобус\"", response, equalTo(3));
    }

    @Test
    void uploadFileBuses() {
        var response = busService.uploadFileBuses(uploadFile);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Загрузка данных для записей Автобус\"", response, is(true)),
                () -> Assertions.assertThrows(InvalidFileTypeException.class, () -> busService.uploadFileBuses(uploadFileNull))
        );
    }
}
