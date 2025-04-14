package ru.innopolis.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import ru.innopolis.dto.driver.DriverRequest;
import ru.innopolis.dto.driver.DriverResponse;
import ru.innopolis.entity.Driver;
import ru.innopolis.repository.DriverRepository;
import ru.innopolis.service.impl.DriverServiceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@SpringBootTest(classes = {DriverServiceImpl.class})
class DriverServiceTest {

    @MockitoBean
    private DriverRepository driverRepository;

    @Autowired
    private DriverServiceImpl driverService;


    @Test
    void createDriver() {
        Mockito.when(driverRepository.save(Mockito.any(Driver.class))).thenReturn(driver);
        var response = driverService.createDriver(driverRequest);

        Assertions.assertEquals(driverResponse.getSurname(), response.getSurname());
    }

    @Test
    void findByIdDriver() {
        Mockito.when(driverRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(driver));
        var response = driverService.findByIdDriver(1L);

        Assertions.assertEquals(Optional.of(driverResponse).orElseThrow().getId(), response.orElseThrow().getId());
    }

    @Test
    void findAllDrivers() {
        Mockito.when(driverRepository.findAll()).thenReturn(driversAll);
        var response = driverService.findAllDrivers();

        Assertions.assertEquals(driverResponseAll.get(0).getId(), response.get(0).getId());
    }

    @Test
    void updateDriver() {
        Mockito.when(driverRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(driver));
        Mockito.when(driverRepository.save(Mockito.any(Driver.class))).thenReturn(driverNew);
        var response = driverService.updateDriver(1L, driverRequestNew);

        Assertions.assertEquals(Optional.of(driverResponseNew).orElseThrow().getSurname(), response.orElseThrow().getSurname());
    }

    @Test
    void deleteByIdDriver() {
        Mockito.when(driverRepository.deleteByIdAsSoft(Mockito.any(Long.class))).thenReturn(1);
        var response = driverService.deleteByIdDriver(1L);

        Assertions.assertTrue(response);
    }

    @Test
    void deleteAllDrivers() {
        Mockito.when(driverRepository.deleteAllAsSoft()).thenReturn(1);
        var response = driverService.deleteAllDrivers();

        Assertions.assertEquals(1, response);
    }

    @Test
    void uploadFileDrivers() throws IOException {
        var response = driverService.uploadFileDrivers(driverFile);

        Assertions.assertEquals(true, response);
    }


    // create
    private final Driver driver = new Driver(1L, "Иванов", "Иван", 50);
    private final DriverRequest driverRequest = new DriverRequest("Иванов", "Иван", 50);
    private final DriverResponse driverResponse = new DriverResponse(1L, "Иванов", "Иван", 50);


    // findAll
    private final List<Driver> driversAll = List.of(
            new Driver(1L, "Иванов", "Иван", 50),
            new Driver(2L, "Петров", "Петр", 35)
    );
    private final List<DriverResponse> driverResponseAll = List.of(
            new DriverResponse(1L, "Иванов", "Иван", 50),
            new DriverResponse(2L, "Петров", "Петр", 35)
    );

    // update
    private final Driver driverNew = new Driver(1L, "Пупкин", "Вася", 22);
    private final DriverRequest driverRequestNew = new DriverRequest("Пупкин", "Вася", 22);
    private final DriverResponse driverResponseNew = new DriverResponse(1L, "Пупкин", "Вася", 22);

    // uploadFile
    private final MockMultipartFile driverFile = new MockMultipartFile(
            "file",
            "driversRequest.json",
            "application/json",
            """
                [
                    {
                        "surname": "Сергеев",
                        "name": "Сергей",
                        "age": 52
                    }
                ]
            """.getBytes()
    );

}