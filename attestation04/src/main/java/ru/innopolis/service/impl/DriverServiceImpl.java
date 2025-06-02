package ru.innopolis.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.innopolis.dto.driver.DriverRequest;
import ru.innopolis.dto.driver.DriverResponse;
import ru.innopolis.entity.Driver;
import ru.innopolis.exception.InvalidFileTypeException;
import ru.innopolis.repository.DriverRepository;
import ru.innopolis.service.DriverService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public DriverResponse createDriver(DriverRequest driverRequest) {
        Driver driver = driverRepository.save(
                new Driver(
                        null,
                        driverRequest.getSurname(),
                        driverRequest.getName(),
                        driverRequest.getAge()
                )
        );

        return new DriverResponse(
                driver.getId(),
                driver.getSurname(),
                driver.getName(),
                driver.getAge()
        );
    }

    @Override
    public Optional<DriverResponse> findByIdDriver(Long id) {
        try {
            Driver driver = driverRepository.findById(id).orElseThrow();

            return Optional.of(
                    new DriverResponse(
                            driver.getId(),
                            driver.getSurname(),
                            driver.getName(),
                            driver.getAge()
                    )
            );
        } catch (EmptyResultDataAccessException | NoSuchElementException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<DriverResponse> findAllDrivers() {
        List<Driver> driverList = driverRepository.findAll();
        List<DriverResponse> driverResponseList = new ArrayList<>();
        driverList.forEach(driver -> driverResponseList.add(
                new DriverResponse(
                        driver.getId(),
                        driver.getSurname(),
                        driver.getName(),
                        driver.getAge()
                )
        ));

        return driverResponseList;
    }

    @Override
    public Optional<DriverResponse> updateDriver(Long id, DriverRequest driverRequest) {
        try {
            driverRepository.findById(id).orElseThrow();

            Driver driver = driverRepository.save(
                    new Driver(
                            id,
                            driverRequest.getSurname(),
                            driverRequest.getName(),
                            driverRequest.getAge()
                    )
            );

            return Optional.of(
                    new DriverResponse(
                            driver.getId(),
                            driver.getSurname(),
                            driver.getName(),
                            driver.getAge()
                    )
            );
        } catch (EmptyResultDataAccessException | NoSuchElementException e) {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public Boolean deleteByIdDriver(Long id) {
        Integer result = driverRepository.deleteByIdAsSoft(id);

        return result == 1;
    }

    @Transactional
    @Override
    public Integer deleteAllDrivers() {
        return driverRepository.deleteAllAsSoft();
    }

    @Override
    public Boolean uploadFileDrivers(MultipartFile file) throws InvalidFileTypeException {
        try {
            File fileParse = new File("C:\\Users\\k.golub\\IdeaProjects\\JavaEEinnopolis\\attestation03\\src\\main\\resources\\json\\fileParse.json");
            file.transferTo(fileParse);

            CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, DriverRequest.class);

            List<DriverRequest> driverRequestList = objectMapper.readValue(fileParse, listType);
            driverRequestList.forEach(driverRequest ->
                    driverRepository.save(
                            new Driver(
                                    null,
                                    driverRequest.getSurname(),
                                    driverRequest.getName(),
                                    driverRequest.getAge()
                            )
                    )
            );

            return true;
        } catch (IOException e) {
            throw new InvalidFileTypeException("Некорректный тип файла");
        }
    }
}
