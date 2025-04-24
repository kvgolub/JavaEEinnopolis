package ru.innopolis.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import ru.innopolis.dto.busType.BusTypeRequest;
import ru.innopolis.dto.busType.BusTypeResponse;
import ru.innopolis.entity.BusType;
import ru.innopolis.repository.BusTypeRepository;
import ru.innopolis.service.impl.BusTypeServiceImpl;

import java.util.List;
import java.util.Optional;


@SpringBootTest(classes = {BusTypeServiceImpl.class})
class BusTypeServiceTest {

    @MockitoBean
    private BusTypeRepository busTypeRepository;

    @Autowired
    private BusTypeServiceImpl busTypeService;


    @Test
    void createBusType() {
        Mockito.when(busTypeRepository.save(Mockito.any(BusType.class))).thenReturn(busType);
        var response = busTypeService.createBusType(busTypeRequest);

        Assertions.assertEquals(busTypeResponse.getNameType(), response.getNameType());
    }

    @Test
    void findByIdBusType() {
        Mockito.when(busTypeRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(busType));
        var response = busTypeService.findByIdBusType(1L);

        Assertions.assertEquals(Optional.of(busTypeResponse).orElseThrow().getId(), response.orElseThrow().getId());
    }

    @Test
    void findAllBusTypes() {
        Mockito.when(busTypeRepository.findAll()).thenReturn(busTypesAll);
        var response = busTypeService.findAllBusTypes();

        Assertions.assertEquals(busTypeResponseAll.get(0).getId(), response.get(0).getId());
    }

    @Test
    void updateBusType() {
        Mockito.when(busTypeRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(busType));
        Mockito.when(busTypeRepository.save(Mockito.any(BusType.class))).thenReturn(busTypeNew);
        var response = busTypeService.updateBusType(1L, busTypeRequestNew);

        Assertions.assertEquals(Optional.of(busTypeResponseNew).orElseThrow().getNameType(), response.orElseThrow().getNameType());
    }

    @Test
    void deleteByIdBusType() {
        Mockito.when(busTypeRepository.deleteByIdAsSoft(Mockito.any(Long.class))).thenReturn(1);
        var response = busTypeService.deleteByIdBusType(1L);

        Assertions.assertTrue(response);
    }

    @Test
    void deleteAllBusTypes() {
        Mockito.when(busTypeRepository.deleteAllAsSoft()).thenReturn(1);
        var response = busTypeService.deleteAllBusTypes();

        Assertions.assertEquals(1, response);
    }


    // create
    private final BusType busType = new BusType(1L, "амфибия");
    private final BusTypeRequest busTypeRequest = new BusTypeRequest("амфибия");
    private final BusTypeResponse busTypeResponse = new BusTypeResponse(1L, "амфибия");


    // findAll
    private final List<BusType> busTypesAll = List.of(
            new BusType(1L, "амфибия"),
            new BusType(2L, "полярный")
    );
    private final List<BusTypeResponse> busTypeResponseAll = List.of(
            new BusTypeResponse(1L, "амфибия"),
            new BusTypeResponse(2L, "полярный")
    );

    // update
    private final BusType busTypeNew = new BusType(1L, "миниавтобус");
    private final BusTypeRequest busTypeRequestNew = new BusTypeRequest("миниавтобус");
    private final BusTypeResponse busTypeResponseNew = new BusTypeResponse(1L, "миниавтобус");

}