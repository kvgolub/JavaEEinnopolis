package ru.innopolis.controller;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.innopolis.controller.impl.BusTypeControllerImpl;
import ru.innopolis.dto.busType.BusTypeRequest;
import ru.innopolis.dto.busType.BusTypeResponse;
import ru.innopolis.service.impl.BusTypeServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest
@ContextConfiguration(classes = {BusTypeControllerImpl.class})
public class BusTypeControllerTest {

    @MockitoBean
    private BusTypeServiceImpl busTypeService;

    @Autowired
    private MockMvc mvc;


    @WithMockUser(authorities={"USER"})
    @Test
    void createBusTypeControllerTest() throws Exception {
        Mockito.when(busTypeService.createBusType(Mockito.any(BusTypeRequest.class))).thenReturn(busTypeResponse);

        mvc.perform(post("/api/v1/bus_station/bus_type")
                        .contentType("application/json")
                        .content(busTypeRequestBody)
                        .with(csrf())
                )
                .andExpect(status().isCreated());
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void findByIdBusTypeControllerTest() throws Exception {
        Mockito.when(busTypeService.findByIdBusType(Mockito.any(Long.class))).thenReturn(Optional.of(busTypeResponse));

        mvc.perform(get("/api/v1/bus_station/bus_type/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(busTypeResponseJson));
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void findAllBusTypesControllerTest() throws Exception {
        Mockito.when(busTypeService.findAllBusTypes()).thenReturn(busTypeResponseAll);

        mvc.perform(get("/api/v1/bus_station/bus_type/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void updateBusTypeControllerTest() throws Exception {
        Mockito.when(busTypeService.updateBusType(Mockito.any(Long.class), Mockito.any(BusTypeRequest.class))).thenReturn(Optional.of(busTypeResponseNew));

        mvc.perform(put("/api/v1/bus_station/bus_type/1")
                        .contentType("application/json")
                        .content(busTypeRequestNewBody)
                        .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(content().json(busTypeResponseNewJson));
    }

    @WithMockUser(authorities={"ADMIN"})
    @Test
    void deleteByIdBusTypeControllerTest() throws Exception {
        Mockito.when(busTypeService.deleteByIdBusType(Mockito.any(Long.class))).thenReturn(true);

        mvc.perform(delete("/api/v1/bus_station/bus_type/1").with(csrf()))
                .andExpect(status().isOk());
    }

    @WithMockUser(authorities={"ADMIN"})
    @Test
    void deleteAllBusTypesControllerTest() throws Exception {
        Mockito.when(busTypeService.deleteAllBusTypes()).thenReturn(1);

        mvc.perform(delete("/api/v1/bus_station/bus_type/all").with(csrf()))
                .andExpect(status().isOk());
    }


    // create
    private final BusTypeResponse busTypeResponse = new BusTypeResponse(1L, "амфибия");
    private final String busTypeRequestBody = """
                            {
                            	"nameType": "амфибия"
                            }
                        """;

    // find
    private final String busTypeResponseJson = """
                            {
                            	"id": 1,
                            	"nameType": "амфибия"
                            }
                        """;

    // findAll
    private final List<BusTypeResponse> busTypeResponseAll = List.of(
            new BusTypeResponse(1L, "амфибия"),
            new BusTypeResponse(2L, "полярный")
    );

    // update
    private final BusTypeResponse busTypeResponseNew = new BusTypeResponse(1L, "миниавтобус");
    private final String busTypeRequestNewBody = """
                            {
                                "nameType": "миниавтобус"
                            }
                        """;
    private final String busTypeResponseNewJson = """
                            {
                                "id": 1,
                                "nameType": "миниавтобус"
                            }
                        """;

}
