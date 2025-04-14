package ru.innopolis.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import ru.innopolis.dto.busStop.BusStopRequest;
import ru.innopolis.dto.busStop.BusStopResponse;
import ru.innopolis.entity.BusStop;
import ru.innopolis.repository.BusStopRepository;
import ru.innopolis.service.impl.BusStopServiceImpl;

import java.util.List;
import java.util.Optional;


@SpringBootTest(classes = {BusStopServiceImpl.class})
class BusStopServiceTest {

    @MockitoBean
    private BusStopRepository busStopRepository;

    @Autowired
    private BusStopServiceImpl busStopService;


    @Test
    void createBusStop() {
        Mockito.when(busStopRepository.save(Mockito.any(BusStop.class))).thenReturn(busStop);
        var response = busStopService.createBusStop(busStopRequest);

        Assertions.assertEquals(busStopResponse.getNameStop(), response.getNameStop());
    }

    @Test
    void findByIdBusStop() {
        Mockito.when(busStopRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(busStop));
        var response = busStopService.findByIdBusStop(1L);

        Assertions.assertEquals(Optional.of(busStopResponse).orElseThrow().getId(), response.orElseThrow().getId());
    }

    @Test
    void findAllBusStops() {
        Mockito.when(busStopRepository.findAll()).thenReturn(busStopsAll);
        var response = busStopService.findAllBusStops();

        Assertions.assertEquals(busStopResponseAll.get(0).getId(), response.get(0).getId());
    }

    @Test
    void updateBusStop() {
        Mockito.when(busStopRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(busStop));
        Mockito.when(busStopRepository.save(Mockito.any(BusStop.class))).thenReturn(busStopNew);
        var response = busStopService.updateBusStop(1L, busStopRequestNew);

        Assertions.assertEquals(Optional.of(busStopResponseNew).orElseThrow().getNameStop(), response.orElseThrow().getNameStop());
    }

    @Test
    void deleteByIdBusStop() {
        Mockito.when(busStopRepository.deleteByIdAsSoft(Mockito.any(Long.class))).thenReturn(1);
        var response = busStopService.deleteByIdBusStop(1L);

        Assertions.assertTrue(response);
    }

    @Test
    void deleteAllBusStops() {
        Mockito.when(busStopRepository.deleteAllAsSoft()).thenReturn(1);
        var response = busStopService.deleteAllBusStops();

        Assertions.assertEquals(1, response);
    }


    // create
    private final BusStop busStop = new BusStop(1L, "Завод Черный экран");
    private final BusStopRequest busStopRequest = new BusStopRequest("Завод Черный экран");
    private final BusStopResponse busStopResponse = new BusStopResponse(1L, "Завод Черный экран");


    // findAll
    private final List<BusStop> busStopsAll = List.of(
            new BusStop(1L, "Завод Черный экран"),
            new BusStop(2L, "Речной вокзал")
    );
    private final List<BusStopResponse> busStopResponseAll = List.of(
            new BusStopResponse(1L, "Завод Черный экран"),
            new BusStopResponse(2L, "Речной вокзал")
    );

    // update
    private final BusStop busStopNew = new BusStop(1L, "ул.Вишнёвая");
    private final BusStopRequest busStopRequestNew = new BusStopRequest("ул.Вишнёвая");
    private final BusStopResponse busStopResponseNew = new BusStopResponse(1L, "ул.Вишнёвая");

}