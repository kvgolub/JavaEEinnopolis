package ru.innopolis.service.module;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.innopolis.entity.Driver;
import ru.innopolis.exception.InvalidFileTypeException;
import ru.innopolis.repository.DriverRepository;
import ru.innopolis.service.impl.DriverServiceImpl;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import static ru.innopolis.utils.BusVariables.uploadFileNull;
import static ru.innopolis.utils.DriverVariables.*;


@ExtendWith(MockitoExtension.class)
class DriverServiceTest {

    @Mock
    private DriverRepository driverRepository;

    @InjectMocks
    private DriverServiceImpl driverService;


    @Test
    void createDriver() {
        Mockito.when(driverRepository.save(Mockito.any(Driver.class))).thenReturn(create);
        var response = driverService.createDriver(createRequest);

        assertThat("Тестирование \"Создание записи Водитель\"", response.getSurname(), equalTo(createResponse.getSurname()));
    }

    @Test
    void findByIdDriver() {
        Mockito.when(driverRepository.findById(1L)).thenReturn(Optional.of(find));
        Mockito.when(driverRepository.findById(100L)).thenReturn(Optional.empty());
        var response = driverService.findByIdDriver(1L);
        var response2 = driverService.findByIdDriver(100L);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Поиск записи Водитель - запись есть\"", response.orElseThrow().getId(), equalTo(Optional.of(findResponse).orElseThrow().getId())),
                () -> assertThat("Тестирование \"Поиск записи Водитель - записи нет\"", response2, equalTo(Optional.empty()))
        );
    }

    @Test
    void findAllDrivers() {
        Mockito.when(driverRepository.findAll()).thenReturn(findAll);
        var response = driverService.findAllDrivers();

        assertThat("Тестирование \"Поиск всех записей Водитель - количество\"", response.size(), equalTo(2));
        assertThat("Тестирование \"Поиск всех записей Водитель - первая запись\"", response.get(0).getId(), equalTo(findAllResponse.get(0).getId()));
    }

    @Test
    void updateDriver() {
        Mockito.when(driverRepository.findById(1L)).thenReturn(Optional.of(find));
        Mockito.when(driverRepository.findById(100L)).thenReturn(Optional.empty());
        Mockito.when(driverRepository.save(Mockito.any(Driver.class))).thenReturn(update);
        var response = driverService.updateDriver(1L, updateRequest);
        var response2 = driverService.updateDriver(100L, updateRequest);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Обновление записи Водитель - запись есть\"", response.orElseThrow().getSurname(), equalTo(Optional.of(updateResponse).orElseThrow().getSurname())),
                () -> assertThat("Тестирование \"Обновление записи Водитель - записи нет\"", response2, equalTo(Optional.empty()))
        );
    }

    @Test
    void deleteByIdDriver() {
        Mockito.when(driverRepository.deleteByIdAsSoft(1L)).thenReturn(1);
        Mockito.when(driverRepository.deleteByIdAsSoft(100L)).thenReturn(0);
        var response = driverService.deleteByIdDriver(1L);
        var response2 = driverService.deleteByIdDriver(100L);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Удаление записи Водитель - запись есть\"", response, is(true)),
                () -> assertThat("Тестирование \"Удаление записи Водитель - записи нет\"", response2, is(false))
        );
    }

    @Test
    void deleteAllDrivers() {
        Mockito.when(driverRepository.deleteAllAsSoft()).thenReturn(11);
        var response = driverService.deleteAllDrivers();

        assertThat("Тестирование \"Удаление всех записей Водитель\"", response, equalTo(11));
    }

    @Test
    void uploadFileDrivers() {
        var response = driverService.uploadFileDrivers(uploadFile);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Загрузка данных для записей Водитель\"", response, is(true)),
                () -> Assertions.assertThrows(InvalidFileTypeException.class, () -> driverService.uploadFileDrivers(uploadFileNull))
        );
    }
}
