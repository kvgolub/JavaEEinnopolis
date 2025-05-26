package ru.innopolis.service.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.dto.busStop.BusStopRequest;
import ru.innopolis.dto.busStop.BusStopResponse;
import ru.innopolis.entity.BusStop;
import ru.innopolis.repository.BusStopRepository;
import ru.innopolis.service.BusStopService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class BusStopServiceImpl implements BusStopService {

    private final BusStopRepository busStopRepository;

    public BusStopServiceImpl(BusStopRepository busStopRepository) {
        this.busStopRepository = busStopRepository;
    }


    @Override
    public BusStopResponse createBusStop(BusStopRequest busStopRequest) {
        BusStop busStop = busStopRepository.save(
                new BusStop(
                        null,
                        busStopRequest.getNameStop()
                )
        );

        return new BusStopResponse(
                busStop.getId(),
                busStop.getNameStop()
        );
    }

    @Override
    public Optional<BusStopResponse> findByIdBusStop(Long id) {
        try {
            BusStop busStop = busStopRepository.findById(id).orElseThrow();

            return Optional.of(
                    new BusStopResponse(
                            busStop.getId(),
                            busStop.getNameStop()
                    )
            );
        } catch (EmptyResultDataAccessException | NoSuchElementException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<BusStopResponse> findAllBusStops() {
        List<BusStop> busStopList = busStopRepository.findAll();
        List<BusStopResponse> busStopResponseList = new ArrayList<>();
        busStopList.forEach(busStop -> busStopResponseList.add(
                new BusStopResponse(
                        busStop.getId(),
                        busStop.getNameStop()
                )
        ));

        return busStopResponseList;
    }

    @Override
    public Optional<BusStopResponse> updateBusStop(Long id, BusStopRequest busStopRequest) {
        try {
            busStopRepository.findById(id).orElseThrow();

            BusStop busStop = busStopRepository.save(
                    new BusStop(
                            id,
                            busStopRequest.getNameStop()
                    )
            );

            return Optional.of(
                    new BusStopResponse(
                            busStop.getId(),
                            busStop.getNameStop()
                    )
            );
        } catch (EmptyResultDataAccessException | NoSuchElementException e) {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public Boolean deleteByIdBusStop(Long id) {
        Integer result = busStopRepository.deleteByIdAsSoft(id);

        return result == 1;
    }

    @Transactional
    @Override
    public Integer deleteAllBusStops() {
        return busStopRepository.deleteAllAsSoft();
    }
}
