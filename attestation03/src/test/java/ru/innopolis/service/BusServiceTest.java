package ru.innopolis.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import ru.innopolis.dto.bus.BusRequest;
import ru.innopolis.dto.bus.BusResponse;
import ru.innopolis.entity.Bus;
import ru.innopolis.repository.BusRepository;
import ru.innopolis.service.impl.BusServiceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@SpringBootTest(classes = {BusServiceImpl.class})
class BusServiceTest {

    @MockitoBean
    private BusRepository busRepository;

    @Autowired
    private BusServiceImpl busService;


    @Test
    void createBus() {
        Mockito.when(busRepository.save(Mockito.any(Bus.class))).thenReturn(bus);
        var response = busService.createBus(busRequest);

        Assertions.assertEquals(busResponse.getModel(), response.getModel());
    }

    @Test
    void findByIdBus() {
        Mockito.when(busRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(bus));
        var response = busService.findByIdBus(1L);

        Assertions.assertEquals(Optional.of(busResponse).orElseThrow().getId(), response.orElseThrow().getId());
    }

    @Test
    void findAllBuses() {
        Mockito.when(busRepository.findAll()).thenReturn(busesAll);
        var response = busService.findAllBuses();

        Assertions.assertEquals(busResponseAll.get(0).getId(), response.get(0).getId());
    }

    @Test
    void updateBus() {
        Mockito.when(busRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(bus));
        Mockito.when(busRepository.save(Mockito.any(Bus.class))).thenReturn(busNew);
        var response = busService.updateBus(1L, busRequestNew);

        Assertions.assertEquals(Optional.of(busResponseNew).orElseThrow().getModel(), response.orElseThrow().getModel());
    }

    @Test
    void deleteByIdBus() {
        Mockito.when(busRepository.deleteByIdAsSoft(Mockito.any(Long.class))).thenReturn(1);
        var response = busService.deleteByIdBus(1L);

        Assertions.assertTrue(response);
    }

    @Test
    void deleteAllBuses() {
        Mockito.when(busRepository.deleteAllAsSoft()).thenReturn(1);
        var response = busService.deleteAllBuses();

        Assertions.assertEquals(1, response);
    }

    @Test
    void uploadFileBuses() throws IOException {
        var response = busService.uploadFileBuses(busFile);

        Assertions.assertEquals(true, response);
    }


    // create
    private final Bus bus = new Bus(1L, 1L, "Газель", "a001aa", 20, 1000000.00);
    private final BusRequest busRequest = new BusRequest(1L, "Газель", "a001aa", 20, 1000000.00);
    private final BusResponse busResponse = new BusResponse(1L, 1L, "Газель", "a001aa", 20, 1000000.00);


    // findAll
    private final List<Bus> busesAll = List.of(
            new Bus(1L, 1L, "Газель", "a001aa", 20, 1000000.00),
            new Bus(2L, 2L, "ЛиАЗ", "в002aa", 50, 1500000.05)
    );
    private final List<BusResponse> busResponseAll = List.of(
            new BusResponse(1L, 1L, "Газель", "a001aa", 20, 1000000.00),
            new BusResponse(2L, 2L, "ЛиАЗ", "в002aa", 50, 1500000.05)
    );

    // update
    private final Bus busNew = new Bus(1L, 4L, "Икарус", "м055ма", 200,3000000.00);
    private final BusRequest busRequestNew = new BusRequest(4L, "Икарус", "м055ма", 200,3000000.00);
    private final BusResponse busResponseNew = new BusResponse(1L, 4L, "Икарус", "м055ма", 200,3000000.00);

    // uploadFile
    private final MockMultipartFile busFile = new MockMultipartFile(
            "file",
            "busesRequest.json",
            "application/json",
            """
                [
                    {
                        "type": 1,
                        "model": "Автобус-100",
                        "regNumber": "A001AA",
                        "passengerCapacity": 100,
                        "price": 20000000.00
                    }
                ]
            """.getBytes()
    );

}