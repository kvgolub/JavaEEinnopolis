package ru.innopolis.service.module;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.innopolis.entity.BusType;
import ru.innopolis.repository.BusTypeRepository;
import ru.innopolis.service.impl.BusTypeServiceImpl;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import static ru.innopolis.utils.BusTypeVariables.*;


@ExtendWith(MockitoExtension.class)
class BusTypeServiceTest {

    @Mock
    private BusTypeRepository busTypeRepository;

    @InjectMocks
    private BusTypeServiceImpl busTypeService;


    @Test
    void createBusType() {
        Mockito.when(busTypeRepository.save(Mockito.any(BusType.class))).thenReturn(create);
        var response = busTypeService.createBusType(createRequest);

        assertThat("Тестирование \"Создание записи Тип автобуса\"", response.getNameType(), equalTo(createResponse.getNameType()));
    }

    @Test
    void findByIdBusType() {
        Mockito.when(busTypeRepository.findById(1L)).thenReturn(Optional.of(find));
        Mockito.when(busTypeRepository.findById(100L)).thenReturn(Optional.empty());
        var response = busTypeService.findByIdBusType(1L);
        var response2 = busTypeService.findByIdBusType(100L);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Поиск записи Тип автобуса - запись есть\"", response.orElseThrow().getId(), equalTo(Optional.of(findResponse).orElseThrow().getId())),
                () -> assertThat("Тестирование \"Поиск записи Тип автобуса - записи нет\"", response2, equalTo(Optional.empty()))
        );
    }

    @Test
    void findAllBusTypes() {
        Mockito.when(busTypeRepository.findAll()).thenReturn(findAll);
        var response = busTypeService.findAllBusTypes();

        assertThat("Тестирование \"Поиск всех записей Тип автобуса - количество\"", response.size(), equalTo(2));
        assertThat("Тестирование \"Поиск всех записей Тип автобуса - первая запись\"", response.get(0).getId(), equalTo(findAllResponse.get(0).getId()));
    }

    @Test
    void updateBusType() {
        Mockito.when(busTypeRepository.findById(1L)).thenReturn(Optional.of(find));
        Mockito.when(busTypeRepository.findById(100L)).thenReturn(Optional.empty());
        Mockito.when(busTypeRepository.save(Mockito.any(BusType.class))).thenReturn(update);
        var response = busTypeService.updateBusType(1L, updateRequest);
        var response2 = busTypeService.updateBusType(100L, updateRequest);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Обновление записи Тип автобуса - запись есть\"", response.orElseThrow().getNameType(), equalTo(Optional.of(updateResponse).orElseThrow().getNameType())),
                () -> assertThat("Тестирование \"Обновление записи Тип автобуса - записи нет\"", response2, equalTo(Optional.empty()))
        );
    }

    @Test
    void deleteByIdBusType() {
        Mockito.when(busTypeRepository.deleteByIdAsSoft(1L)).thenReturn(1);
        Mockito.when(busTypeRepository.deleteByIdAsSoft(100L)).thenReturn(0);
        var response = busTypeService.deleteByIdBusType(1L);
        var response2 = busTypeService.deleteByIdBusType(100L);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Удаление записи Тип автобуса - запись есть\"", response, is(true)),
                () -> assertThat("Тестирование \"Удаление записи Тип автобуса - записи нет\"", response2, is(false))
        );
    }

    @Test
    void deleteAllBusTypes() {
        Mockito.when(busTypeRepository.deleteAllAsSoft()).thenReturn(11);
        var response = busTypeService.deleteAllBusTypes();

        assertThat("Тестирование \"Удаление всех записей Тип автобуса\"", response, equalTo(11));
    }
}
