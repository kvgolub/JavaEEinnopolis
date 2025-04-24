package ru.innopolis.service;

import org.springframework.web.multipart.MultipartFile;
import ru.innopolis.dto.bus.BusRequest;
import ru.innopolis.dto.bus.BusResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


public interface BusService {

    BusResponse createBus(BusRequest busRequest);
    Optional<BusResponse> findByIdBus(Long id);
    List<BusResponse> findAllBuses();
    Optional<BusResponse> updateBus(Long id, BusRequest busRequest);
    Boolean deleteByIdBus(Long id);
    Integer deleteAllBuses();

    Boolean uploadFileBuses (MultipartFile file) throws IOException;

}
