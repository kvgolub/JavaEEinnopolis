package ru.innopolis.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.innopolis.dto.bus.BusRequest;
import ru.innopolis.dto.bus.BusResponse;
import ru.innopolis.entity.Bus;
import ru.innopolis.repository.BusRepository;
import ru.innopolis.service.BusService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class BusServiceImpl implements BusService {

    private final BusRepository busRepository;

    public BusServiceImpl(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public BusResponse createBus(BusRequest busRequest) {
        Bus bus = busRepository.save(
                new Bus(
                        null,
                        busRequest.getType(),
                        busRequest.getModel(),
                        busRequest.getRegNumber(),
                        busRequest.getPassengerCapacity(),
                        busRequest.getPrice()
                )
        );

        return new BusResponse(
                bus.getId(),
                bus.getType(),
                bus.getModel(),
                bus.getRegNumber(),
                bus.getPassengerCapacity(),
                bus.getPrice()
        );
    }

    @Override
    public Optional<BusResponse> findByIdBus(Long id) {
        try {
            Bus bus = busRepository.findById(id).orElseThrow();

            return Optional.of(
                    new BusResponse(
                            bus.getId(),
                            bus.getType(),
                            bus.getModel(),
                            bus.getRegNumber(),
                            bus.getPassengerCapacity(),
                            bus.getPrice()
                    )
            );
        } catch (EmptyResultDataAccessException | NoSuchElementException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<BusResponse> findAllBuses() {
        List<Bus> busList = busRepository.findAll();
        List<BusResponse> busResponseList = new ArrayList<>();
        busList.forEach(bus -> busResponseList.add(
                new BusResponse(
                        bus.getId(),
                        bus.getType(),
                        bus.getModel(),
                        bus.getRegNumber(),
                        bus.getPassengerCapacity(),
                        bus.getPrice()
                )
        ));

        return busResponseList;
    }

    @Override
    public Optional<BusResponse> updateBus(Long id, BusRequest busRequest) {
        try {
            Bus getBus = busRepository.findById(id).orElseThrow();

            if (getBus.getId() != null) {
                Bus bus = busRepository.save(
                        new Bus(
                                id,
                                busRequest.getType(),
                                busRequest.getModel(),
                                busRequest.getRegNumber(),
                                busRequest.getPassengerCapacity(),
                                busRequest.getPrice()
                        )
                );

                return Optional.of(
                        new BusResponse(
                                bus.getId(),
                                bus.getType(),
                                bus.getModel(),
                                bus.getRegNumber(),
                                bus.getPassengerCapacity(),
                                bus.getPrice()
                        )
                );
            } else {
                return Optional.empty();
            }
        } catch (EmptyResultDataAccessException | NoSuchElementException e) {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public Boolean deleteByIdBus(Long id) {
        Integer result = busRepository.deleteByIdAsSoft(id);

        return result == 1;
    }

    @Transactional
    @Override
    public Integer deleteAllBuses() {
        return busRepository.deleteAllAsSoft();
    }


    @Override
    public Boolean uploadFileBuses(MultipartFile file) throws IOException {
        try {
            File fileParse = new File("C:\\Users\\k.golub\\IdeaProjects\\JavaEEinnopolis\\attestation03\\src\\main\\resources\\json\\fileParse.json");
            file.transferTo(fileParse);

            CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, BusRequest.class);

            List<BusRequest> busRequestList = objectMapper.readValue(fileParse, listType);
            busRequestList.forEach(busRequest ->
                    busRepository.save(
                            new Bus(
                                    null,
                                    busRequest.getType(),
                                    busRequest.getModel(),
                                    busRequest.getRegNumber(),
                                    busRequest.getPassengerCapacity(),
                                    busRequest.getPrice()
                            )
                    )
            );

            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
