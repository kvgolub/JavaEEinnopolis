package ru.innopolis.service;

import org.springframework.web.multipart.MultipartFile;
import ru.innopolis.dto.driver.DriverRequest;
import ru.innopolis.dto.driver.DriverResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


public interface DriverService {

    DriverResponse createDriver(DriverRequest driverRequest);
    Optional<DriverResponse> findByIdDriver(Long id);
    List<DriverResponse> findAllDrivers();
    Optional<DriverResponse> updateDriver(Long id, DriverRequest driverRequest);
    Boolean deleteByIdDriver(Long id);
    Integer deleteAllDrivers();

    Boolean uploadFileDrivers (MultipartFile file) throws IOException;

}
